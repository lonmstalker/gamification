package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.*
import io.lonmstalker.gamification.exception.WalletException
import io.lonmstalker.gamification.gateway.NftGateway
import io.lonmstalker.gamification.gateway.TransferGateway
import io.lonmstalker.gamification.gateway.WalletGateway
import io.lonmstalker.gamification.model.*
import io.lonmstalker.gamification.repository.TransactionRepository
import io.lonmstalker.gamification.repository.WalletRepository
import io.lonmstalker.gamification.service.ActionService
import io.lonmstalker.gamification.service.AdminWalletService
import io.lonmstalker.gamification.utils.UserUtils
import io.lonmstalker.gamification.utils.component1
import io.lonmstalker.gamification.utils.component2
import io.lonmstalker.gamification.utils.validateTransfer
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import reactor.util.function.Tuples
import java.util.*

@Service
class AdminWalletServiceImpl(
    private val nftGateway: NftGateway,
    private val walletGateway: WalletGateway,
    private val modelConverter: ModelConverter,
    private val transferGateway: TransferGateway,
    private val walletRepository: WalletRepository,
    private val adminActionServiceImpl: ActionService,
    private val transactionRepository: TransactionRepository,
) : AdminWalletService {
    private lateinit var adminPayOperation: ActionDto

    @EventListener(ApplicationReadyEvent::class)
    fun init() {
        val pageRp = runBlocking {
            adminActionServiceImpl.getAll(
                PageRq(
                    filter = mutableSetOf(
                        FilterRq("Начисление администратором", "name", FilterRq.Operation.EQUAL)
                    )
                )
            ).awaitFirst()
        }
        this.adminPayOperation = pageRp.content.first()
    }

    @Transactional
    override fun reward(rqBody: AdminRewardDto) = UserUtils.getCurrentUserId()
        .flatMap { adminId ->
            (if (rqBody.actionId == null) Mono.empty() else this.adminActionServiceImpl.getOne(UUID.fromString(rqBody.actionId)))
                .defaultIfEmpty(this.adminPayOperation.copy())
                .flatMap { action ->
                    Mono.zip(
                        this.walletRepository.findById(adminId),
                        this.walletRepository.findById(UUID.fromString(rqBody.walletId))
                    ).flatMap { (wallet, adminWallet) ->
                        this.walletGateway.getBalance(wallet.publicKey)
                            .handle<List<Tuple2<TransferRqDto, TransferType>>> { balance, sink ->
                                val rqList = mutableListOf<Tuple2<TransferRqDto, TransferType>>()

                                this.updateAction(action, adminWallet.privateKey, wallet.publicKey, rqBody, rqList)
                                if (rqList.isEmpty()) return@handle sink.error(WalletException("Вы ничего не переводите"))
                                if (!validateTransfer(sink, action, balance)) {
                                    return@handle
                                }

                                sink.next(rqList)
                            }.flatMapMany { rqList ->
                                val generateNft =
                                    if (rqBody.tokens.isNullOrEmpty()) Flux.empty() else Flux.fromIterable(rqBody.tokens!!)
                                        .map { this.mapGenerateNftRq(wallet, it, action) }
                                Flux.mergeSequential(
                                    generateNft,
                                    Flux.fromIterable(rqList).concatMap { this.mapTransferRq(wallet, action, it) }
                                ).concatMap { it ->
                                        this.transactionRepository.save(it as TransactionHistory)
                                            .map { this.modelConverter.transactionToDto(it) }
                                    }
                            }.collectList()
                    }
                }
        }

    @Transactional
    override fun delete(walletId: UUID): Mono<Boolean> =
        this.walletRepository.deleteById(walletId).thenReturn(true)

    @Transactional
    override fun change(walletDto: WalletDto): Mono<WalletDto> =
        this.walletRepository.save(this.modelConverter.dtoToWallet(walletDto))
            .map { this.modelConverter.walletToDto(it) }

    private fun mapTransferRq(
        wallet: Wallet, action: ActionDto, tuple: Tuple2<TransferRqDto, TransferType>
    ): Mono<TransactionHistory> {
        val rs = tuple.t1
        return this.transferGateway.transfer(tuple.t1, tuple.t2)
            .map { rp ->
                TransactionHistory(
                    walletId = wallet.walletId!!, hash = rp.transactionHash, actionId = action.actionId!!
                ).apply {
                    when (tuple.t2) {
                        TransferType.MATIC -> this.matic = rs.amount!!.toDouble()
                        TransferType.RUBLE -> this.coins = rs.amount!!.toDouble()
                        else -> {}
                    }
                }
            }
    }

    private fun mapGenerateNftRq(
        wallet: Wallet, generateNftDto: GenerateNftDto, action: ActionDto
    ): Mono<TransactionHistory> =
        this.nftGateway.generateNft(GenerateBalanceRqDto(wallet.publicKey, generateNftDto.uri, generateNftDto.count))
            .map { rp ->
                TransactionHistory(
                    walletId = wallet.walletId!!, hash = rp.transactionHash, actionId = action.actionId!!
                )
            }

    private fun updateAction(
        action: ActionDto, adminPrivateKey: String, userPublicKey: String,
        rqBody: AdminRewardDto, rqList: MutableList<Tuple2<TransferRqDto, TransferType>>
    ) {
        if (rqBody.coins != null) {
            action.coins = rqBody.coins
            rqList.add(
                Tuples.of(
                    TransferRqDto(
                        fromPrivateKey = adminPrivateKey, toPublicKey = userPublicKey, amount = action.coins
                    ), TransferType.RUBLE
                )
            )
        }
        if (rqBody.matic != null) {
            action.matic = rqBody.matic
            rqList.add(
                Tuples.of(
                    TransferRqDto(
                        fromPrivateKey = adminPrivateKey, toPublicKey = userPublicKey, amount = action.matic
                    ), TransferType.MATIC
                )
            )
        }
    }
}
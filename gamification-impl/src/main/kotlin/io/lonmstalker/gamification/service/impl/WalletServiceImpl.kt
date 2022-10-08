package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.*
import io.lonmstalker.gamification.gateway.NftGateway
import io.lonmstalker.gamification.gateway.WalletGateway
import io.lonmstalker.gamification.model.TransactionHistory
import io.lonmstalker.gamification.model.Wallet
import io.lonmstalker.gamification.repository.WalletRepository
import io.lonmstalker.gamification.service.FilterService
import io.lonmstalker.gamification.service.WalletService
import io.lonmstalker.gamification.utils.UserUtils
import io.lonmstalker.gamification.utils.toPage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.util.*

@Service
class WalletServiceImpl(
    private val walletGateway: WalletGateway,
    private val filterService: FilterService,
    private val modelConverter: ModelConverter,
    private val walletRepository: WalletRepository
) : WalletService {

    @Transactional
    override fun getCurrentWallet(): Mono<WalletDto> = UserUtils.getCurrentUserInfo()
        .flatMap { user ->
            this.walletRepository.findById(user.userId)
                .switchIfEmpty(Mono.defer {
                    this.walletGateway.createWallet().flatMap {
                        this.walletRepository.save(
                            Wallet(
                                walletId = user.userId,
                                userRole = Role.USER.name,
                                privateKey = it.privateKey,
                                publicKey = it.publicKey,
                                firstName = user.firstName,
                                lastName = user.lastName
                            )
                        )
                    }
                })
        }.flatMap { wallet -> this.fillRpBalance(wallet) }

    @Transactional(readOnly = true)
    override fun getTeamWallets(teamType: TeamType?, pageRq: PageRq?): Mono<Page<WalletDto>> = pageRq?.let {
        if (teamType != null) {
            if (pageRq.filter == null) it.filter = HashSet(1)
            it.filter!!.add(FilterRq(teamType.name, "teamType", FilterRq.Operation.EQUAL))
        }
    }.run { filterService.findAll(pageRq, Wallet::class.java) { modelConverter.walletToDto(it) } }
        .map { it.toPage(pageRq) }

    @Transactional(readOnly = true)
    override fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        this.filterService.findAll(pageRq, TransactionHistory::class.java) { this.modelConverter.transactionToDto(it) }
            .map { it.toPage(pageRq) }

    @Transactional(readOnly = true)
    override fun getColleagueHistory(walletId: UUID, pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        pageRq?.let {
            if (it.filter == null) it.filter = HashSet(1)
            it.filter!!.add(FilterRq(walletId, "walletId", FilterRq.Operation.EQUAL))
        }
            .run {
                filterService.findAll(pageRq, TransactionHistory::class.java) { modelConverter.transactionToDto(it) }
            }
            .map { it.toPage(pageRq) }

    override fun getOne(walletId: UUID): Mono<WalletDto> = this.walletRepository.findById(walletId)
        .map { this.modelConverter.walletToDto(it) }

    private fun fillRpBalance(wallet: Wallet) = this.getBalance(wallet.publicKey)
        .map {
            val dto = this.modelConverter.walletToDto(wallet)
            dto.nft = it.t2.balance
            dto.maticAmount = it.t1.maticAmount
            dto.coinsAmount = it.t1.coinsAmount
            dto
        }

    private fun getBalance(publicKey: String) = Mono.zip(
        this.walletGateway.getBalance(publicKey),
        this.walletGateway.getNftBalance(publicKey)
    )
}
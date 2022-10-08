package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.*
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
import reactor.util.function.Tuples
import java.util.*

@Service
class WalletServiceImpl(
    private val walletGateway: WalletGateway,
    private val filterService: FilterService,
    private val modelConverter: ModelConverter,
    private val walletRepository: WalletRepository
) : WalletService {

    @Transactional
    override fun getCurrentWallet(): Mono<WalletDto> = UserUtils.getCurrentUserInfo().flatMap { user ->
        this.walletRepository.findById(user.userId).switchIfEmpty(Mono.defer {
            this.walletGateway.createWallet().flatMap {
                this.walletRepository.save(
                    Wallet(
                        userId = user.userId,
                        userRole = Role.USER.name,
                        privateKey = it.privateKey,
                        publicKey = it.publicKey,
                        firstName = user.firstName,
                        lastName = user.lastName
                    )
                )
            }
        })
    }.map { this.modelConverter.walletToDto(it) }

    @Transactional(readOnly = true)
    override fun getTeamWallets(teamType: TeamType, pageRq: PageRq?): Mono<Page<WalletDto>> = pageRq?.let {
        if (pageRq.filter == null) it.filter = HashSet(1)
        it.filter!!.add(FilterRq(teamType.name, "teamType", FilterRq.Operation.EQUAL))
    }.run { filterService.findAll(pageRq, Wallet::class.java) { modelConverter.walletToDto(it) } }
        .map { it.toPage(pageRq) }

    override fun getTopWallets(pageRq: PageRq?): Mono<Page<WalletDto>> {
        if (pageRq == null) return Mono.empty()

        val offset = if (pageRq.offset == 0) 1 else pageRq.offset.toLong()
        val page = if (pageRq.page == 0) 1 else pageRq.page.toLong()

        return Mono.zip(
            this.walletRepository.count(), this.walletRepository.findTopWallet(offset, page)
        ).map { Tuples.of(it.t1, it.t2.map { this.modelConverter.walletToDto(it) }.toMutableList()).toPage(pageRq) }
    }

    @Transactional(readOnly = true)
    override fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        this.filterService.findAll(pageRq, TransactionHistory::class.java) { this.modelConverter.transactionToDto(it) }
            .map { it.toPage(pageRq) }

    @Transactional(readOnly = true)
    override fun getColleagueHistory(walletId: UUID, pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        pageRq?.let {
            if (it.filter == null) it.filter = HashSet(1)
            it.filter!!.add(FilterRq(walletId, "walletId", FilterRq.Operation.EQUAL))
        }.run { filterService.findAll(pageRq, TransactionHistory::class.java) { modelConverter.transactionToDto(it) } }
            .map { it.toPage(pageRq) }
}
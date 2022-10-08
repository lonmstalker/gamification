package io.lonmstalker.gamification.controller.external

import io.lonmstalker.gamification.controller.external.WalletController
import io.lonmstalker.gamification.dto.*
import io.lonmstalker.gamification.service.WalletService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class WalletControllerImpl(
    private val walletService: WalletService
) : WalletController {

    override fun getCurrentWallet(): Mono<WalletDto> =
        this.walletService.getCurrentWallet()

    override fun getTeamWallets(teamType: TeamType?, pageRq: PageRq?): Mono<Page<WalletDto>> =
        this.walletService.getTeamWallets(teamType, pageRq)

    override fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        this.walletService.getCurrentHistory(pageRq)

    override fun getColleagueHistory(walletId: String, pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        this.walletService.getColleagueHistory(UUID.fromString(walletId), pageRq)

    override fun getOne(walletId: String): Mono<WalletDto> =
        this.walletService.getOne(UUID.fromString(walletId))
}
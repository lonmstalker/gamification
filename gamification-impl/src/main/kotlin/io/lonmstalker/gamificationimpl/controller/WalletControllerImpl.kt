package io.lonmstalker.gamificationimpl.controller

import io.lonmstalker.gamificationapi.controller.external.WalletController
import io.lonmstalker.gamificationapi.dto.*
import io.lonmstalker.gamificationimpl.converter.ModelConverter
import io.lonmstalker.gamificationimpl.service.WalletService
import io.lonmstalker.gamificationimpl.utils.UserUtils
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class WalletControllerImpl(
    private val walletService: WalletService
) : WalletController {

    override fun getCurrentWallet(): Mono<WalletDto> =
        this.walletService.getCurrentWallet()

    override fun getTeamWallets(teamType: TeamType, pageRq: PageRq?): Mono<Page<WalletDto>> =
        this.walletService.getTeamWallets(teamType, pageRq)

    override fun getTopWallets(pageRq: PageRq?): Mono<Page<WalletDto>> =
        this.walletService.getTopWallets(pageRq)

    override fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> =
        this.walletService.getCurrentHistory(pageRq)

}
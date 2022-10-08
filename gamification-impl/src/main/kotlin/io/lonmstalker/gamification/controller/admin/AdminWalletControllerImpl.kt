package io.lonmstalker.gamification.controller.admin

import io.lonmstalker.gamification.dto.AdminRewardDto
import io.lonmstalker.gamification.dto.TransactionHistoryDto
import io.lonmstalker.gamification.dto.WalletDto
import io.lonmstalker.gamification.service.AdminWalletService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class AdminWalletControllerImpl(
    private val adminWalletService: AdminWalletService
) : AdminWalletController {

    override fun reward(rqBody: AdminRewardDto): Mono<MutableList<TransactionHistoryDto>> =
        this.adminWalletService.reward(rqBody)

    override fun delete(walletId: String): Mono<Boolean> =
        this.adminWalletService.delete(UUID.fromString(walletId))

    override fun change(walletDto: WalletDto): Mono<WalletDto> =
        this.adminWalletService.change(walletDto)
}
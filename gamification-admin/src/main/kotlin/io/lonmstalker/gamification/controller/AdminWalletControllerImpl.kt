package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.admin.AdminWalletController
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.dto.TransactionHistoryDto
import io.lonmstalker.gamification.dto.WalletDto
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class AdminWalletControllerImpl : AdminWalletController {

    override fun reward(actionId: String, walletId: String): Mono<WalletDto> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flux<WalletDto> {
        TODO("Not yet implemented")
    }

    override fun getOne(walletId: String): Mono<WalletDto> {
        TODO("Not yet implemented")
    }

    override fun delete(walletId: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getHistory(pageRq: PageRq?, walletId: String?): Mono<Page<TransactionHistoryDto>> {
        TODO("Not yet implemented")
    }
}
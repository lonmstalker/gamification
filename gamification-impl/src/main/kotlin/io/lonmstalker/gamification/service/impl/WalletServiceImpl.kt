package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.dto.*
import io.lonmstalker.gamification.service.WalletService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WalletServiceImpl : WalletService {

    override fun getCurrentWallet(): Mono<WalletDto> {
        TODO("Not yet implemented")
    }

    override fun getTeamWallets(teamType: TeamType, pageRq: PageRq?): Mono<Page<WalletDto>> {
        TODO("Not yet implemented")
    }

    override fun getTopWallets(pageRq: PageRq?): Mono<Page<WalletDto>> {
        TODO("Not yet implemented")
    }

    override fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>> {
        TODO("Not yet implemented")
    }
}
package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.*
import reactor.core.publisher.Mono

interface WalletService {
    fun getCurrentWallet(): Mono<WalletDto>

    fun getTeamWallets(teamType: TeamType, pageRq: PageRq?): Mono<Page<WalletDto>>

    fun getTopWallets(pageRq: PageRq?): Mono<Page<WalletDto>>

    fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>>
}
package io.lonmstalker.gamificationimpl.service

import io.lonmstalker.gamificationapi.dto.*
import reactor.core.publisher.Mono

interface WalletService {
    fun getCurrentWallet(): Mono<WalletDto>

    fun getTeamWallets(teamType: TeamType, pageRq: PageRq?): Mono<Page<WalletDto>>

    fun getTopWallets(pageRq: PageRq?): Mono<Page<WalletDto>>

    fun getCurrentHistory(pageRq: PageRq?): Mono<Page<TransactionHistoryDto>>
}
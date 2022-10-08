package io.lonmstalker.gamification.gateway

import io.lonmstalker.gamification.model.*
import reactor.core.publisher.Mono

interface WalletGateway {
    fun createWallet(): Mono<WalletRpDto>

    fun getBalance(publicKey: String): Mono<BalanceRpDto>

    fun getNftBalance(publicKey: String): Mono<NftBalanceRpDto>

    fun getTransactionHistory(rq: ApiPageDto): Mono<List<TransactionRpDto>>
}
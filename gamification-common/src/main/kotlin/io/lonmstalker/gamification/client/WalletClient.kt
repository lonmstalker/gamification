package io.lonmstalker.gamification.client

import com.fasterxml.jackson.databind.JsonNode
import io.lonmstalker.gamification.constants.ClientEndpointConstants.WALLET_V1
import io.lonmstalker.gamification.model.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "wallet-client", url = "\${app.blockchainApiUrl}")
interface WalletClient {

    @PostMapping("$WALLET_V1/new")
    fun createWallet(): Mono<WalletRpDto>

    @GetMapping("$WALLET_V1/{publicKey}/balance")
    fun getBalance(@PathVariable publicKey: String): Mono<BalanceRpDto>

    @GetMapping("$WALLET_V1/{publicKey}/nft/balance")
    fun getNftBalance(@PathVariable publicKey: String): Mono<NftBalanceRpDto>

    @PostMapping("$WALLET_V1/{publicKey}/history")
    fun getTransactionHistory(@RequestBody rq: ApiPageDto): Mono<List<TransactionRpDto>>
}
package io.lonmstalker.gamification.client

import io.lonmstalker.gamification.constants.ClientEndpointConstants.TRANSFER_V1
import io.lonmstalker.gamification.model.TransactionStatusRqDto
import io.lonmstalker.gamification.model.TransferRpDto
import io.lonmstalker.gamification.model.TransferRqDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "transfer-client", url = "\${app.blockchainApiUrl}")
interface TransferClient {

    @PostMapping("$TRANSFER_V1/{transferType}")
    fun transfer(@RequestBody rq: TransferRqDto, @PathVariable transferType: String): Mono<TransferRpDto>

    @PostMapping("$TRANSFER_V1/status/{transactionHash}")
    fun checkTransactionStatus(@PathVariable transactionHash: String): Mono<TransactionStatusRqDto>
}
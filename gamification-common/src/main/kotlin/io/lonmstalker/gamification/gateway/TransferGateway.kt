package io.lonmstalker.gamification.gateway

import io.lonmstalker.gamification.model.TransactionStatusRqDto
import io.lonmstalker.gamification.model.TransferRpDto
import io.lonmstalker.gamification.model.TransferRqDto
import io.lonmstalker.gamification.model.TransferType
import reactor.core.publisher.Mono

interface TransferGateway {
    fun transfer(rq: TransferRqDto, transferType: TransferType): Mono<TransferRpDto>

    fun checkTransactionStatus(transactionHash: String): Mono<TransactionStatusRqDto>
}
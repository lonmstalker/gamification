package io.lonmstalker.gamification.gateway

import io.lonmstalker.gamification.model.GenerateBalanceRqDto
import io.lonmstalker.gamification.model.NftInfoRpDto
import io.lonmstalker.gamification.model.TransactionInfoRpDto
import io.lonmstalker.gamification.model.TransferRpDto
import reactor.core.publisher.Mono

interface NftGateway {
    fun generateNft(rq: GenerateBalanceRqDto): Mono<TransferRpDto>

    fun getInfo(tokenId: Int): Mono<NftInfoRpDto>

    fun getNftList(transactionHash: String): Mono<TransactionInfoRpDto>
}
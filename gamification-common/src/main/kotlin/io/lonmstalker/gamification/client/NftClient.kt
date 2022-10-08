package io.lonmstalker.gamification.client

import io.lonmstalker.gamification.constants.ClientEndpointConstants.NFT_V1
import io.lonmstalker.gamification.model.GenerateBalanceRqDto
import io.lonmstalker.gamification.model.NftInfoRpDto
import io.lonmstalker.gamification.model.TransactionInfoRpDto
import io.lonmstalker.gamification.model.TransferRpDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "nft-client", url = "\${app.blockchainApiUrl}")
interface NftClient {

    @PostMapping("$NFT_V1/generate")
    fun generateNft(@RequestBody rq: GenerateBalanceRqDto): Mono<TransferRpDto>

    @GetMapping("$NFT_V1/{tokenId}")
    fun getInfo(@PathVariable tokenId: Int): Mono<NftInfoRpDto>

    @GetMapping("$NFT_V1/generate/{transactionHash}")
    fun getNftList(@PathVariable transactionHash: String): Mono<TransactionInfoRpDto>
}
package io.lonmstalker.gamification.gateway.impl

import io.lonmstalker.gamification.client.NftClient
import io.lonmstalker.gamification.constants.Errors.ERROR_GENERATE_NFT
import io.lonmstalker.gamification.constants.Errors.ERROR_GENERATE_NFT_LOG
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_NFT_INFO
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_NFT_INFO_LOG
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_NFT_LIST
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_NFT_LOG
import io.lonmstalker.gamification.exception.NftException
import io.lonmstalker.gamification.gateway.NftGateway
import io.lonmstalker.gamification.model.GenerateBalanceRqDto
import io.lonmstalker.gamification.model.NftInfoRpDto
import io.lonmstalker.gamification.model.TransactionInfoRpDto
import io.lonmstalker.gamification.model.TransferRpDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/**
 * Компонент для интеграции с эндпоинтами nft
 */
@Component
class NftGatewayImpl(
    private val nftClient: NftClient
) : NftGateway {

    override fun generateNft(rq: GenerateBalanceRqDto): Mono<TransferRpDto> =
        this.nftClient.generateNft(rq)
            .onErrorMap {
                LOGGER.error(ERROR_GENERATE_NFT_LOG, it.message)
                NftException(ERROR_GENERATE_NFT)
            }

    override fun getInfo(tokenId: Int): Mono<NftInfoRpDto> =
        this.nftClient.getInfo(tokenId)
            .onErrorMap {
                LOGGER.error(ERROR_GET_NFT_INFO_LOG, it.message)
                NftException(ERROR_GET_NFT_INFO)
            }

    override fun getNftList(transactionHash: String): Mono<TransactionInfoRpDto> =
        this.nftClient.getNftList(transactionHash)
            .onErrorMap {
                LOGGER.error(ERROR_GET_NFT_LOG, it.message)
                NftException(ERROR_GET_NFT_LIST)
            }

    companion object {
        @JvmStatic
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}
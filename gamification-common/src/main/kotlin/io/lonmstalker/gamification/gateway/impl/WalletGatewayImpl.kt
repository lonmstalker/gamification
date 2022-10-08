package io.lonmstalker.gamification.gateway.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamification.client.WalletClient
import io.lonmstalker.gamification.constants.Errors.ERROR_CREATE_WALLET
import io.lonmstalker.gamification.constants.Errors.ERROR_CREATE_WALLET_LOG
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_NFT_WALLET
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_NFT_WALLET_LOG
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_TRANSACTIONS
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_TRANSACTIONS_LOG
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_WALLET
import io.lonmstalker.gamification.constants.Errors.ERROR_GET_WALLET_LOG
import io.lonmstalker.gamification.exception.WalletException
import io.lonmstalker.gamification.gateway.WalletGateway
import io.lonmstalker.gamification.model.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/**
 * Компонент для интеграции с эндпоинтами кошелька
 */
@Component
class WalletGatewayImpl(
    private val walletClient: WalletClient
) : WalletGateway {

    override fun createWallet(): Mono<WalletRpDto> = this.walletClient.createWallet()
        .onErrorMap {
            LOGGER.error(ERROR_CREATE_WALLET_LOG, it.message)
            WalletException(ERROR_CREATE_WALLET)
        }

    override fun getBalance(publicKey: String): Mono<BalanceRpDto> = this.walletClient.getBalance(publicKey)
        .onErrorMap {
            LOGGER.error(ERROR_GET_WALLET_LOG, it.message)
            WalletException(ERROR_GET_WALLET)
        }

    override fun getNftBalance(publicKey: String): Mono<NftBalanceRpDto> = this.walletClient.getNftBalance(publicKey)
        .onErrorMap {
            LOGGER.error(ERROR_GET_NFT_WALLET_LOG, it.message)
            WalletException(ERROR_GET_NFT_WALLET)
        }

    override fun getTransactionHistory(rq: ApiPageDto): Mono<List<TransactionRpDto>> = this.walletClient.getTransactionHistory(rq)
            .onErrorMap {
                LOGGER.error(ERROR_GET_TRANSACTIONS_LOG, it.message)
                WalletException(ERROR_GET_TRANSACTIONS)
            }

    companion object {
        @JvmStatic
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}
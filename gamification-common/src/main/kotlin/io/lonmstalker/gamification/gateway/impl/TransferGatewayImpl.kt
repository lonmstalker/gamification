package io.lonmstalker.gamification.gateway.impl

import io.lonmstalker.gamification.client.TransferClient
import io.lonmstalker.gamification.constants.Errors.ERROR_CHECK_TRANSFER
import io.lonmstalker.gamification.constants.Errors.ERROR_CHECK_TRANSFER_LOG
import io.lonmstalker.gamification.constants.Errors.ERROR_TRANSFER
import io.lonmstalker.gamification.constants.Errors.ERROR_TRANSFER_LOG
import io.lonmstalker.gamification.exception.TransactionException
import io.lonmstalker.gamification.gateway.TransferGateway
import io.lonmstalker.gamification.model.TransactionStatusRqDto
import io.lonmstalker.gamification.model.TransferRpDto
import io.lonmstalker.gamification.model.TransferRqDto
import io.lonmstalker.gamification.model.TransferType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/**
 * Компонент для интеграции с эндпоинтами обмена
 */
@Component
class TransferGatewayImpl(
    private val transferClient: TransferClient
) : TransferGateway {

    override fun transfer(rq: TransferRqDto, transferType: TransferType): Mono<TransferRpDto> =
        this.transferClient.transfer(rq, transferType.name)
            .onErrorMap {
                LOGGER.error(ERROR_TRANSFER_LOG, transferType.name, it.message)
                TransactionException(String.format(ERROR_TRANSFER, transferType.name))
            }

    override fun checkTransactionStatus(transactionHash: String): Mono<TransactionStatusRqDto> =
        this.transferClient.checkTransactionStatus(transactionHash)
            .onErrorMap {
                LOGGER.error(ERROR_CHECK_TRANSFER_LOG, transactionHash, it.message)
                TransactionException(ERROR_CHECK_TRANSFER)
            }

    companion object {
        @JvmStatic
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}
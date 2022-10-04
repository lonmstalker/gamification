package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonCreator
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

data class TransactionHistoryDto @JsonCreator constructor(

    @field:Schema(description = "Id транзакции")
    val transactionId: UUID?,

    @field:Schema(description = "Id предмета")
    val itemId: UUID?,

    @field:Schema(description = "Монет в транзакции")
    val money: Long?,

    @field:Schema(description = "Nft в транзакции")
    val nft: Int?,

    @field:Schema(description = "Id пользователя, на кого выполнена транзакция")
    val userId: UUID,

    @field:Schema(description = "Id инициатора транзакции")
    val transactionInitiator: UUID?,

    @field:Schema(description = "Когда выполнена")
    val createdDate: LocalDateTime?
)

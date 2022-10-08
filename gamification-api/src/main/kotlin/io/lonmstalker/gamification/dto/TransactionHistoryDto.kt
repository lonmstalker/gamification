package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransactionHistoryDto @JsonCreator constructor(

    @field:Schema(description = "Id транзакции")
    val transactionId: UUID?,

    @field:Schema(description = "Id предмета")
    val itemId: UUID?,

    @field:Schema(description = "Монет в транзакции")
    val coins: Long?,

    @field:Schema(description = "Nft в транзакции")
    val nft: Int?,

    @field:Schema(description = "Id токена ft")
    val tokenId: Int? = null,

    @field:Schema(description = "Статус выполнения транзакции в блокчейне")
    val status: String? = null,

    @field:Schema(description = "Id действия")
    val actionId: UUID,

    @field:Schema(description = "Награда в MATIC")
    val matic: Double?,

    @field:Schema(description = "Id кошелька, на который выполнена транзакция")
    val walletId: UUID,

    @field:Schema(description = "Id инициатора транзакции")
    val transactionInitiator: UUID?,

    @field:Schema(description = "Когда выполнена")
    val createdDate: LocalDateTime?
)

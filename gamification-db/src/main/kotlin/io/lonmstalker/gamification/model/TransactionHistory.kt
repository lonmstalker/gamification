package io.lonmstalker.gamification.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("transaction_history")
data class TransactionHistory constructor(

    @field:Id
    @field:Column("transaction_id")
    val transactionId: UUID? = null,

    @field:Column("item_id")
    val itemId: UUID? = null,

    @field:Column("coins")
    var coins: Double? = null,

    @field:Column("matic")
    var matic: Double? = null,

    @field:Column("token_id")
    var tokenId: Int? = null,

    @field:Column("status")
    val status: String? = null,

    @field:Column("hash")
    val hash: String,

    @field:Column("action_id")
    val actionId: UUID,

    @field:CreatedBy
    @field:Column("wallet_id")
    val walletId: UUID,

    @field:CreatedBy
    @field:Column("transaction_initiator")
    val transactionInitiator: UUID? = null,

    @field:CreatedDate
    @field:Column("created_date")
    val createdDate: LocalDateTime? = null
)

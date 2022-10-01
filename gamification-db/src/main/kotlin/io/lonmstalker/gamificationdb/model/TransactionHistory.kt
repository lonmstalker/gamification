package io.lonmstalker.gamificationdb.model

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
    @field:Column("id")
    val transactionId: UUID? = null,

    @field:Column("item_id")
    val itemId: UUID?,

    @field:Column("money")
    val money: Long?,

    @field:Column("nft")
    val nft: Int?,

    @field:CreatedBy
    @field:Column("user_id")
    val userId: UUID,

    @field:CreatedBy
    @field:Column("transaction_initiator")
    val transactionInitiator: UUID?,

    @field:CreatedDate
    @field:Column("created_date")
    val createdDate: LocalDateTime?
)

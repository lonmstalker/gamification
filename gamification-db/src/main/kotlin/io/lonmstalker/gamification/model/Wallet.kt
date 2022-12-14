package io.lonmstalker.gamification.model

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("wallet")
data class Wallet(

    @Id
    @field:Column("wallet_id")
    val walletId: UUID?,

    @field:Column("first_name")
    val firstName: String,

    @field:Column("last_name")
    val lastName: String? = null,

    @field:Column("middle_name")
    val middleName: String? = null,

    @field:Column("phone_number")
    val phoneNumber: String? = null,

    @field:Column("email")
    val email: String? = null,

    @field:Column("role")
    val userRole: String,

    @field:Column("private_key")
    val privateKey: String,

    @field:Column("public_key")
    val publicKey: String,

    @field:Version
    @field:Column("version")
    val version: Long = 0
)
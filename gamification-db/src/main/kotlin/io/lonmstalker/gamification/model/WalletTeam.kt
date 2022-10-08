package io.lonmstalker.gamification.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("wallet_teams")
class WalletTeam(

    @field:Id
    @field:Column("id")
    val id: UUID?,

    @field:Column("wallet_id")
    val walletId: UUID,

    @field:Column("team_id")
    val teamId: UUID,
)
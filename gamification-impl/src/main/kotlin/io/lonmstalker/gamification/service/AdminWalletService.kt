package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.AdminRewardDto
import io.lonmstalker.gamification.dto.GenerateNftDto
import io.lonmstalker.gamification.dto.TransactionHistoryDto
import io.lonmstalker.gamification.dto.WalletDto
import reactor.core.publisher.Mono
import java.util.UUID

interface AdminWalletService {

    fun reward(rqBody: AdminRewardDto): Mono<MutableList<TransactionHistoryDto>>

    fun delete(walletId: UUID): Mono<Boolean>

    fun change(walletDto: WalletDto): Mono<WalletDto>
}
package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Wallet
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface WalletRepository : ReactiveCrudRepository<Wallet, UUID> {
}
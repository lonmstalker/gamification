package io.lonmstalker.gamificationimpl.repository

import io.lonmstalker.gamificationdb.model.Wallet
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface WalletRepository : ReactiveCrudRepository<Wallet, UUID> {
}
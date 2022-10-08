package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Wallet
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import reactor.core.publisher.Mono
import java.util.*

interface WalletRepository : ReactiveSortingRepository<Wallet, UUID> {

    @Query("SELECT w.* FROM wallet w ORDER BY COALESCE(w.coins,0) + COALESCE(w.matic,0) + COALESCE(w.nft,0) LIMIT :limit OFFSET :offset")
    fun findTopWallet(limit: Long, offset: Long): Mono<MutableList<Wallet>>
}
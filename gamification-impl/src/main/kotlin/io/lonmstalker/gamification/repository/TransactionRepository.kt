package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.TransactionHistory
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface TransactionRepository : ReactiveCrudRepository<TransactionHistory, UUID>
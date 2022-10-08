package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Action
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ActionRepository : ReactiveCrudRepository<Action, UUID> {
}
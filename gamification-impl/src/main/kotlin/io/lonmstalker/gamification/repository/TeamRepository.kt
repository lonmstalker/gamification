package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Team
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface TeamRepository : ReactiveCrudRepository<Team, UUID> {
}
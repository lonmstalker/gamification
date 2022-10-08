package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.News
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface NewsRepository : ReactiveCrudRepository<News, UUID> {
}
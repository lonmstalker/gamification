package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Item
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface ItemRepository : ReactiveCrudRepository<Item, UUID>
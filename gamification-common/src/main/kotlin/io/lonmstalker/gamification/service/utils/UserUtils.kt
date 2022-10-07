package io.lonmstalker.gamification.service.utils

import reactor.core.publisher.Mono
import java.util.UUID

object UserUtils {

    fun getCurrentUserId() = Mono.just(UUID.randomUUID())
}
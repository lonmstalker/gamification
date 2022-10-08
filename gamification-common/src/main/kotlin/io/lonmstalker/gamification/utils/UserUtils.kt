package io.lonmstalker.gamification.utils

import io.lonmstalker.gamification.model.UserDto
import reactor.core.publisher.Mono
import java.util.UUID

object UserUtils {
    private val id = UUID.randomUUID()

    fun getCurrentUserId() = Mono.just(id)

    fun getCurrentUserInfo() = Mono.just(UserDto(id, "myName", "myLastName"))
}
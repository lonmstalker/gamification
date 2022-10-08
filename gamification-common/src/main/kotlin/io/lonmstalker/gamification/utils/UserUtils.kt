package io.lonmstalker.gamification.utils

import io.lonmstalker.gamification.model.UserDto
import reactor.core.publisher.Mono
import java.util.UUID

object UserUtils {
    private val id = UUID.fromString("295b5aec-4703-11ed-aea7-0242ac1f0002")

    fun getCurrentUserId() = Mono.just(id)

    fun getCurrentUserInfo() = Mono.just(UserDto(id, "myName", "myLastName"))
}
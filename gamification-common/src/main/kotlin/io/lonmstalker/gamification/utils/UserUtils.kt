package io.lonmstalker.gamification.utils

import io.lonmstalker.gamification.model.UserDto
import reactor.core.publisher.Mono
import java.util.UUID

object UserUtils {
    private val id = UUID.fromString("4e525c84-98b5-4d52-bf4f-315caaed76ff")

    fun getCurrentUserId() = Mono.just(id)

    fun getCurrentUserInfo() = Mono.just(UserDto(id, "myName", "myLastName"))
}
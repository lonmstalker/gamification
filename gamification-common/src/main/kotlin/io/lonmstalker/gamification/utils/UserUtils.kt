package io.lonmstalker.gamification.utils

import io.lonmstalker.gamification.model.UserDto
import reactor.core.publisher.Mono
import java.util.UUID

object UserUtils {
    private val id = UUID.fromString("1424fe48-4709-11ed-8dd7-0242c0a81002")

    fun getCurrentUserId() = Mono.just(id)

    fun getCurrentUserInfo() = Mono.just(UserDto(id, "myName", "myLastName"))
}
package io.lonmstalker.gamification.utils

import io.lonmstalker.gamification.model.UserDto
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import reactor.core.publisher.Mono
import java.util.UUID

object UserUtils {
    private val ID = UUID.randomUUID()
//    fun getCurrentUserId() = this.getCurrentUserInfo().map { it.userId }
fun getCurrentUserId() =Mono.just( ID)

    fun getCurrentUserInfo() = ReactiveSecurityContextHolder.getContext().map { it.authentication as UserDto }
}
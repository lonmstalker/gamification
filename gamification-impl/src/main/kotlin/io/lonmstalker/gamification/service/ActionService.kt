package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.ActionDto
import reactor.core.publisher.Mono
import java.util.*

interface ActionService {
    fun getOne(actionId: UUID): Mono<ActionDto>
}
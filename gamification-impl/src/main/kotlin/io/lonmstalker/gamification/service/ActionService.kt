package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import reactor.core.publisher.Mono
import java.util.*

interface ActionService {

    fun createAction(action: ActionDto): Mono<ActionDto>

    fun updateAction(action: ActionDto): Mono<ActionDto>

    fun getAll(rq: PageRq?): Mono<Page<ActionDto>>

    fun delete(actionId: UUID): Mono<Boolean>

    fun getOne(actionId: UUID): Mono<ActionDto>
}
package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.admin.ActionController
import io.lonmstalker.gamification.dto.ActionDto
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ActionControllerImpl : ActionController {
    override fun createAction(action: ActionDto): Mono<ActionDto> {
        TODO("Not yet implemented")
    }

    override fun updateAction(action: ActionDto, actionId: String): Mono<ActionDto> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flux<ActionDto> {
        TODO("Not yet implemented")
    }

    override fun getOne(actionId: String): Mono<ActionDto> {
        TODO("Not yet implemented")
    }

    override fun delete(actionId: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}
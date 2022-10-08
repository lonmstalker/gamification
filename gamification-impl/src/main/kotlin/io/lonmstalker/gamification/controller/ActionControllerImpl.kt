package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.external.ActionController
import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.service.ActionService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class ActionControllerImpl(
    private val actionService: ActionService
) : ActionController {

    override fun getOne(actionId: String): Mono<ActionDto> = this.actionService.getOne(UUID.fromString(actionId))
}
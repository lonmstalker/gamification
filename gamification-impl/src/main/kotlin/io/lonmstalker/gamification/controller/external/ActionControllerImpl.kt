package io.lonmstalker.gamification.controller.external

import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.service.impl.ActionServiceImpl
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class ActionControllerImpl(
    private val adminActionServiceImpl: ActionServiceImpl
) : ActionController {

    override fun getOne(actionId: String): Mono<ActionDto> = this.adminActionServiceImpl.getOne(UUID.fromString(actionId))
}
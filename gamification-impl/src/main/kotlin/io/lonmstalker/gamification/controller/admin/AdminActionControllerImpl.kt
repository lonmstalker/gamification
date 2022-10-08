package io.lonmstalker.gamification.controller.admin

import io.lonmstalker.gamification.controller.admin.AdminActionController
import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.service.ActionService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
class AdminActionControllerImpl(
    private val actionService: ActionService
) : AdminActionController {

    override fun createAction(action: ActionDto): Mono<ActionDto> =
        this.actionService.createAction(action)

    override fun updateAction(action: ActionDto): Mono<ActionDto> =
        this.actionService.updateAction(action)

    override fun getAll(rq: PageRq?): Mono<Page<ActionDto>> =
        this.actionService.getAll(rq)

    override fun delete(actionId: String): Mono<Boolean> =
        this.actionService.delete(UUID.fromString(actionId))
}
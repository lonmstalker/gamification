package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.admin.AdminActionController
import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.service.AdminActionService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
class AdminActionControllerImpl(
    private val adminActionService: AdminActionService
) : AdminActionController {

    override fun createAction(action: ActionDto): Mono<ActionDto> =
        this.adminActionService.createAction(action)

    override fun updateAction(action: ActionDto): Mono<ActionDto> =
        this.adminActionService.updateAction(action)

    override fun getAll(rq: PageRq?): Mono<Page<ActionDto>> =
        this.adminActionService.getAll(rq)

    override fun delete(actionId: String): Mono<Boolean> =
        this.adminActionService.delete(UUID.fromString(actionId))
}
package io.lonmstalker.gamification.controller.external

import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.EndpointConstants.OPERATIONS_ENDPOINT
import io.lonmstalker.gamification.constants.Views
import io.lonmstalker.gamification.dto.ActionDto
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono

@Tag(name = "Контроллер действия", description = "Контроллер для чтения действий")
interface ActionController {

    @JsonView(Views.USER::class)
    @GetMapping("$OPERATIONS_ENDPOINT/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Действие",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = ActionDto::class))]
    )
    fun getOne(@Parameter(description = "Id действия") @PathVariable actionId: String): Mono<ActionDto>
}
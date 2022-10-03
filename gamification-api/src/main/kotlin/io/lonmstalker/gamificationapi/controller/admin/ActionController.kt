package io.lonmstalker.gamificationapi.controller.admin

import io.lonmstalker.gamificationapi.constants.EndpointConstants.ADMIN_OPERATION_ENDPOINT
import io.lonmstalker.gamificationdb.model.Action
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Tag(name = "Контроллер операций", description = "Контроллер для управления действиями")
interface ActionController {

    @PostMapping(ADMIN_OPERATION_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданное действие",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Action::class))]
    )
    fun createAction(@RequestBody action: Action): Mono<Action>

    @PutMapping("$ADMIN_OPERATION_ENDPOINT/{actionId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Обновленная операция",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Action::class))]
    )
    fun updateAction(
        @RequestBody action: Action,
        @Parameter(description = "Id операции") @PathVariable operationId: String
    ): Mono<Action>

    @GetMapping("$ADMIN_OPERATION_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список действий",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = Action::class)))]
    )
    fun getAll(): Flux<Action>

    @GetMapping("$ADMIN_OPERATION_ENDPOINT{action}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Действие",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Action::class))]
    )
    fun getOne(@Parameter(description = "Id действия") @PathVariable action: String): Mono<Action>
}
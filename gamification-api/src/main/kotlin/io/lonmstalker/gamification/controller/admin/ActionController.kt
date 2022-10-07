package io.lonmstalker.gamification.controller.admin

import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.EndpointConstants.ADMIN_OPERATION_ENDPOINT
import io.lonmstalker.gamification.constants.Views
import io.lonmstalker.gamification.dto.ActionDto
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

    @JsonView(Views.Admin::class)
    @PostMapping(ADMIN_OPERATION_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданное действие",
        responseCode = "201",
        content = [Content(schema = Schema(implementation = ActionDto::class))]
    )
    fun createAction(@RequestBody action: ActionDto): Mono<ActionDto>

    @JsonView(Views.Admin::class)
    @PutMapping("$ADMIN_OPERATION_ENDPOINT/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Обновленная операция",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = ActionDto::class))]
    )
    fun updateAction(
        @RequestBody action: ActionDto,
        @Parameter(description = "Id операции") @PathVariable actionId: String
    ): Mono<ActionDto>

    @JsonView(Views.Admin::class)
    @GetMapping("$ADMIN_OPERATION_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список действий",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = ActionDto::class)))]
    )
    fun getAll(): Flux<ActionDto>

    @JsonView(Views.Admin::class)
    @GetMapping("$ADMIN_OPERATION_ENDPOINT/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Действие",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = ActionDto::class))]
    )
    fun getOne(@Parameter(description = "Id действия") @PathVariable actionId: String): Mono<ActionDto>

    @DeleteMapping("$ADMIN_OPERATION_ENDPOINT/{actionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id действия") @PathVariable actionId: String): Mono<Boolean>
}
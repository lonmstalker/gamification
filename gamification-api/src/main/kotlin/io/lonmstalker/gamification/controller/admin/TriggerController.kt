package io.lonmstalker.gamification.controller.admin

import io.lonmstalker.gamification.constants.EndpointConstants.ADMIN_TRIGGER_ENDPOINT
import io.lonmstalker.gamification.dto.TriggerDto
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

@Tag(name = "Контроллер триггеров", description = "Контроллер для управления триггерами")
interface TriggerController {

    @PostMapping(ADMIN_TRIGGER_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданный триггер",
        responseCode = "201",
        content = [Content(schema = Schema(implementation = TriggerDto::class))]
    )
    fun createTrigger(@RequestBody trigger: TriggerDto): Mono<TriggerDto>

    @PutMapping("$ADMIN_TRIGGER_ENDPOINT/{triggerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Обновленный триггер",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = TriggerDto::class))]
    )
    fun updateTrigger(
        @RequestBody trigger: TriggerDto,
        @Parameter(description = "Id триггера") @PathVariable triggerId: String
    ): Mono<TriggerDto>

    @GetMapping("$ADMIN_TRIGGER_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список триггеров",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = TriggerDto::class)))]
    )
    fun getAll(): Flux<TriggerDto>

    @GetMapping("$ADMIN_TRIGGER_ENDPOINT/{triggerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Триггер",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = TriggerDto::class))]
    )
    fun getOne(@Parameter(description = "Id триггера") @PathVariable triggerId: String): Mono<TriggerDto>

    @DeleteMapping("$ADMIN_TRIGGER_ENDPOINT/{triggerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id команды") @PathVariable triggerId: String): Mono<Boolean>
}
package io.lonmstalker.gamificationapi.controller.admin

import io.lonmstalker.gamificationapi.constants.EndpointConstants.ADMIN_TEAM_ENDPOINT
import io.lonmstalker.gamificationapi.dto.TeamDto
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

@Tag(name = "Контроллер групп", description = "Контроллер для управления группами")
interface TeamController {

    @PostMapping(ADMIN_TEAM_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданная команда",
        responseCode = "201",
        content = [Content(schema = Schema(implementation = TeamDto::class))]
    )
    fun createTeam(@RequestBody team: TeamDto): Mono<TeamDto>

    @PutMapping("$ADMIN_TEAM_ENDPOINT/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Обновленная команда",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = TeamDto::class))]
    )
    fun updateTeam(
        @RequestBody team: TeamDto,
        @Parameter(description = "Id команды") @PathVariable teamId: String
    ): Mono<TeamDto>

    @GetMapping("$ADMIN_TEAM_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список команд",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = TeamDto::class)))]
    )
    fun getAll(): Flux<TeamDto>

    @GetMapping("$ADMIN_TEAM_ENDPOINT/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Команда",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = TeamDto::class))]
    )
    fun getOne(@Parameter(description = "Id команды") @PathVariable teamId: String): Mono<TeamDto>

    @DeleteMapping("$ADMIN_TEAM_ENDPOINT/{teamId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id команды") @PathVariable teamId: String): Mono<Boolean>
}
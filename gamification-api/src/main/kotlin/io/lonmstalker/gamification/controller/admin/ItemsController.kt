package io.lonmstalker.gamification.controller.admin

import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.EndpointConstants.ADMIN_ITEMS_ENDPOINT
import io.lonmstalker.gamification.constants.Views
import io.lonmstalker.gamification.dto.ItemDto
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Tag(name = "Контроллер предметов", description = "Контроллер для управления предметами")
interface ItemsController {

    @JsonView(Views.Admin::class)
    @PostMapping(ADMIN_ITEMS_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданный предмет",
        responseCode = "201",
        content = [Content(schema = Schema(implementation = ItemDto::class))]
    )
    fun createItem(@RequestBody item: ItemDto): Mono<ItemDto>

    @JsonView(Views.Admin::class)
    @PutMapping("$ADMIN_ITEMS_ENDPOINT/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Обновленный предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = ItemDto::class))]
    )
    fun updateItem(
        @RequestBody item: ItemDto,
        @Parameter(description = "Id предмета") @PathVariable itemId: String
    ): Mono<ItemDto>

    @JsonView(Views.Admin::class)
    @GetMapping("$ADMIN_ITEMS_ENDPOINT/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = ItemDto::class))]
    )
    fun getOne(@Parameter(description = "Id предмета") @PathVariable itemId: String): Mono<ItemDto>

    @DeleteMapping("$ADMIN_ITEMS_ENDPOINT/{itemId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id предмета") @PathVariable itemId: String): Mono<Boolean>
}
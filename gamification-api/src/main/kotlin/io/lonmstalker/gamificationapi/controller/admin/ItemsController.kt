package io.lonmstalker.gamificationapi.controller.admin

import io.lonmstalker.gamificationapi.constants.EndpointConstants.ADMIN_ITEMS_ENDPOINT
import io.lonmstalker.gamificationdb.model.Item
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

@Tag(name = "Контроллер предметов", description = "Контроллер для управления предметами")
interface ItemsController {

    @PostMapping(ADMIN_ITEMS_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданный предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Item::class))]
    )
    fun createItem(@RequestBody item: Item): Mono<Item>

    @PutMapping("$ADMIN_ITEMS_ENDPOINT/{itemId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Обновленный предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Item::class))]
    )
    fun updateItem(
        @RequestBody item: Item,
        @Parameter(description = "Id предмета") @PathVariable itemId: String
    ): Mono<Item>

    @GetMapping("$ADMIN_ITEMS_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список команд",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = Item::class)))]
    )
    fun getAll(): Flux<Item>

    @GetMapping("$ADMIN_ITEMS_ENDPOINT{itemId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Item::class))]
    )
    fun getOne(@Parameter(description = "Id предмета") @PathVariable itemId: String): Mono<Item>

    @DeleteMapping("$ADMIN_ITEMS_ENDPOINT{itemId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id предмета") @PathVariable itemId: String): Mono<Boolean>
}
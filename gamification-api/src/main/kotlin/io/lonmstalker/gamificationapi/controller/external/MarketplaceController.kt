package io.lonmstalker.gamificationapi.controller.external

import io.lonmstalker.gamificationapi.constants.EndpointConstants.MARKETPLACE_ENDPOINT
import io.lonmstalker.gamificationapi.dto.ItemDto
import io.lonmstalker.gamificationapi.dto.Page
import io.lonmstalker.gamificationapi.dto.PageRq
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Tag(name = "Контроллер маркетплейса", description = "Контроллер для работы с маркетплейсом")
interface MarketplaceController {

    @GetMapping("$MARKETPLACE_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список предметов",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getAll(@RequestParam(required = false) pageRq: PageRq?): Mono<Page<ItemDto>>

    @PostMapping("$MARKETPLACE_ENDPOINT/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Полученный предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun exchange(
        @PathVariable itemId: String, @RequestParam paymentType: String
    ): Mono<ItemDto>
}
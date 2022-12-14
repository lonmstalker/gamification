package io.lonmstalker.gamification.controller.external

import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.EndpointConstants.MARKETPLACE_ENDPOINT
import io.lonmstalker.gamification.constants.Views
import io.lonmstalker.gamification.dto.ItemBuyRqDto
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Tag(name = "Контроллер маркетплейса", description = "Контроллер для работы с маркетплейсом")
interface MarketplaceController {

    @JsonView(Views.USER::class)
    @PostMapping("$MARKETPLACE_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список предметов",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getAll(@RequestBody(required = false) pageRq: PageRq?): Mono<Page<ItemDto>>

    @JsonView(Views.USER::class)
    @PostMapping("$MARKETPLACE_ENDPOINT/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Полученный предмет",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = ItemBuyRqDto::class))]
    )
    fun exchange(
        @PathVariable itemId: String, @RequestBody rqDto: ItemBuyRqDto
    ): Mono<ItemDto>
}
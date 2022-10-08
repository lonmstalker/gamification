package io.lonmstalker.gamification.controller.admin

import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.EndpointConstants.ADMIN_NEWS_ENDPOINT
import io.lonmstalker.gamification.constants.Views
import io.lonmstalker.gamification.dto.NewsDto
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Tag(name = "Контроллер новостей", description = "Контроллер для управления новостей админами")
interface AdminNewsController {

    @DeleteMapping("$ADMIN_NEWS_ENDPOINT/{newsId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id новости") @PathVariable newsId: String): Mono<Boolean>

    @DeleteMapping("$ADMIN_NEWS_ENDPOINT/comment/{commentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun deleteComment(@Parameter(description = "Id комментария") @PathVariable commentId: String): Mono<Boolean>

    @JsonView(Views.Admin::class)
    @PostMapping(ADMIN_NEWS_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Созданная или обновленная новость",
        responseCode = "201",
        content = [Content(schema = Schema(implementation = NewsDto::class))]
    )
    fun createNews(@RequestBody news: NewsDto): Mono<NewsDto>

}
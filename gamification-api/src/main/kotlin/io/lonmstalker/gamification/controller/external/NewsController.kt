package io.lonmstalker.gamification.controller.external

import io.lonmstalker.gamification.constants.EndpointConstants.NEWS_ENDPOINT
import io.lonmstalker.gamification.dto.CommentDto
import io.lonmstalker.gamification.dto.NewsDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono

@Tag(name = "Контроллер новостей", description = "Контроллер для работы с новостями")
interface NewsController {

    @PostMapping("$NEWS_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список новостей",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getAll(@RequestBody(required = false) pageRq: PageRq?): Mono<Page<NewsDto>>

    @PostMapping("$NEWS_ENDPOINT/{newsId}/comments")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список комментариев под новостью",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getAllComments(
        @PathVariable newsId: String, @RequestBody(required = false) pageRq: PageRq?
    ): Mono<Page<CommentDto>>

    @PostMapping("$NEWS_ENDPOINT/{newsId}/comment")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Добавить или обновить комментарий",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = CommentDto::class))]
    )
    fun saveComment(@PathVariable newsId: String, @RequestBody commentDto: CommentDto): Mono<CommentDto>

    @DeleteMapping("$NEWS_ENDPOINT/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Удалить комментарий",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun deleteComment(@PathVariable commentId: String): Mono<Boolean>

    @PutMapping("$NEWS_ENDPOINT/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Лайкнуть комментарий",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun likeComment(@PathVariable commentId: String): Mono<Boolean>
}
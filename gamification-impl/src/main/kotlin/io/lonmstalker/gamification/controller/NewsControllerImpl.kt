package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.external.NewsController
import io.lonmstalker.gamification.dto.CommentDto
import io.lonmstalker.gamification.dto.NewsDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.service.NewsService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class NewsControllerImpl(private val newsService: NewsService) : NewsController {

    override fun getAll(pageRq: PageRq?): Mono<Page<NewsDto>> =
        this.newsService.getAll(pageRq)

    override fun getAllComments(newsId: String, pageRq: PageRq?): Mono<Page<CommentDto>> =
        this.newsService.getAllComments(UUID.fromString(newsId), pageRq)

    override fun saveComment(newsId: String, commentDto: CommentDto): Mono<CommentDto> =
        this.newsService.saveComment(commentDto.copy(newsId = UUID.fromString(newsId)))

    override fun deleteComment(commentId: String): Mono<Boolean> =
        this.newsService.deleteComment(UUID.fromString(commentId))

    override fun likeComment(commentId: String): Mono<Boolean> =
        this.newsService.likeComment(UUID.fromString(commentId))
}
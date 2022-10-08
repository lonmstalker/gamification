package io.lonmstalker.gamification.controller.admin

import io.lonmstalker.gamification.controller.admin.AdminNewsController
import io.lonmstalker.gamification.dto.NewsDto
import io.lonmstalker.gamification.service.AdminNewsService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class AdminNewsControllerImpl(
    private val adminNewsService: AdminNewsService
) : AdminNewsController {

    override fun delete(newsId: String): Mono<Boolean> =
        this.adminNewsService.delete(UUID.fromString(newsId))

    override fun deleteComment(commentId: String): Mono<Boolean> =
        this.adminNewsService.deleteComment(UUID.fromString(commentId))

    override fun createNews(news: NewsDto): Mono<NewsDto> =
        this.adminNewsService.createNews(news)
}
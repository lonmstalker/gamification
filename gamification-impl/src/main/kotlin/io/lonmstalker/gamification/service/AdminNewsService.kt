package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.NewsDto
import reactor.core.publisher.Mono
import java.util.*

interface AdminNewsService {

    fun delete(newsId: UUID): Mono<Boolean>

    fun deleteComment(commentId: UUID): Mono<Boolean>

    fun createNews(news: NewsDto): Mono<NewsDto>
}
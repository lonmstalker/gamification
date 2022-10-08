package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.CommentDto
import io.lonmstalker.gamification.dto.NewsDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import reactor.core.publisher.Mono
import java.util.UUID

interface NewsService {

    fun getAll(pageRq: PageRq?): Mono<Page<NewsDto>>

    fun getAllComments(newsId: UUID, pageRq: PageRq?): Mono<Page<CommentDto>>

    fun saveComment(commentDto: CommentDto): Mono<CommentDto>

    fun deleteComment(commentId: UUID): Mono<Boolean>

    fun likeComment(commentId: UUID): Mono<Boolean>
}
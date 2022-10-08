package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.NewsDto
import io.lonmstalker.gamification.model.Comment
import io.lonmstalker.gamification.model.News
import io.lonmstalker.gamification.service.AdminNewsService
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.util.*

@Service
class AdminNewsServiceImpl(
    private val modelConverter: ModelConverter,
    private val newsRepository: ReactiveCrudRepository<News, UUID>,
    private val commentRepository: ReactiveCrudRepository<Comment, UUID>
) : AdminNewsService {

    @Transactional
    override fun delete(newsId: UUID): Mono<Boolean> =
        this.newsRepository.deleteById(newsId).thenReturn(true)

    @Transactional
    override fun deleteComment(commentId: UUID): Mono<Boolean> =
        this.commentRepository.deleteById(commentId).thenReturn(true)

    @Transactional
    override fun createNews(news: NewsDto): Mono<NewsDto> =
        this.newsRepository.save(this.modelConverter.dtoToNews(news))
            .map { this.modelConverter.newsToDto(it) }
}
package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.constants.Errors.COMMENTS_CLOSED
import io.lonmstalker.gamification.constants.Errors.COMMENT_FORBIDDEN
import io.lonmstalker.gamification.constants.Errors.ERROR_FORBIDDEN_ACCESS_LOG
import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.*
import io.lonmstalker.gamification.exception.NewsException
import io.lonmstalker.gamification.model.Comment
import io.lonmstalker.gamification.model.News
import io.lonmstalker.gamification.repository.CommentRepository
import io.lonmstalker.gamification.repository.NewsRepository
import io.lonmstalker.gamification.service.FilterService
import io.lonmstalker.gamification.service.NewsService
import io.lonmstalker.gamification.utils.UserUtils
import io.lonmstalker.gamification.utils.toPage
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*
import io.lonmstalker.gamification.utils.component1
import io.lonmstalker.gamification.utils.component2
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional

@Service
class NewsService(
    private val filterService: FilterService,
    private val modelConverter: ModelConverter,
    private val newsRepository: NewsRepository,
    private val commentRepository: CommentRepository,
) : NewsService {

    @Transactional(readOnly = true)
    override fun getAll(pageRq: PageRq?): Mono<Page<NewsDto>> =
        this.filterService.findAll(pageRq, News::class.java) { this.modelConverter.newsToDto(it) }
            .map { it.toPage(pageRq) }

    @Transactional(readOnly = true)
    override fun getAllComments(newsId: UUID, pageRq: PageRq?): Mono<Page<CommentDto>> {
        val filter = FilterRq(newsId, "newsId", FilterRq.Operation.EQUAL)

        val rq = pageRq?.let {
            if (it.filter == null) it.filter = HashSet(1)
            it.filter!!.add(filter)
            it
        } ?: PageRq(filter = mutableSetOf(filter))

        return this.filterService.findAll(rq, Comment::class.java) { this.modelConverter.commentToDto(it) }
            .map { it.toPage(rq) }
    }

    @Transactional
    override fun saveComment(commentDto: CommentDto): Mono<CommentDto> =
        this.newsRepository.findById(commentDto.newsId!!).flatMap {
            if (!it.openComments) {
                return@flatMap Mono.error(NewsException(COMMENTS_CLOSED))
            }
            this.commentRepository.save(this.modelConverter.dtoToComment(commentDto))
        }.map { this.modelConverter.commentToDto(it) }

    @Transactional
    override fun deleteComment(commentId: UUID): Mono<Boolean> =
        Mono.zip(UserUtils.getCurrentUserId(), this.commentRepository.findById(commentId))
            .flatMap { (userId, comment) ->
                if (userId != comment.createdBy) {
                    LOGGER.error(ERROR_FORBIDDEN_ACCESS_LOG, userId, "Удалить чужой комментарий")
                    return@flatMap Mono.error(NewsException(COMMENT_FORBIDDEN))
                }
                this.commentRepository.deleteById(commentId)
            }.thenReturn(true)

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}
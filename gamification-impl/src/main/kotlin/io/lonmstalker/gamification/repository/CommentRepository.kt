package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Comment
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.util.*

interface CommentRepository : ReactiveCrudRepository<Comment, UUID> {

    @Modifying
    @Query("UPDATE comments SET likes = likes + 1 WHERE comment_id = :commentId")
    fun likeComment(commentId: UUID): Mono<Boolean>
}
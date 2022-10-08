package io.lonmstalker.gamification.repository

import io.lonmstalker.gamification.model.Comment
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface CommentRepository : ReactiveCrudRepository<Comment, UUID>
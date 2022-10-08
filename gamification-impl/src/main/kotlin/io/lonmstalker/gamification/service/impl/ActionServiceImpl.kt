package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.repository.ActionRepository
import io.lonmstalker.gamification.service.ActionService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class ActionServiceImpl(
    private val modelConverter: ModelConverter,
    private val actionRepository: ActionRepository
) : ActionService {

    override fun getOne(actionId: UUID): Mono<ActionDto> =
        this.actionRepository.findById(actionId).map { this.modelConverter.actionToDto(it) }
}
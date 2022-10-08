package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.dto.FilterRq
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.model.Action
import io.lonmstalker.gamification.service.AdminActionService
import io.lonmstalker.gamification.service.FilterService
import io.lonmstalker.gamification.utils.toPage
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AdminActionService(
    private val filterService: FilterService,
    private val modelConverter: ModelConverter,
    private val actionRepository: ReactiveCrudRepository<Action, UUID>
) : AdminActionService {

    override fun createAction(action: ActionDto): Mono<ActionDto> =
        this.actionRepository.save(this.modelConverter.dtoToAction(action))
            .map { this.modelConverter.actionToDto(it) }

    override fun updateAction(action: ActionDto): Mono<ActionDto> =
        this.actionRepository.save(this.modelConverter.dtoToAction(action))
            .map { this.modelConverter.actionToDto(it) }

    override fun getAll(rq: PageRq?): Mono<Page<ActionDto>> {
        val filter = FilterRq(false, "oneTime", FilterRq.Operation.EQUAL)

        val resRq = rq?.apply { this.filter?.add(filter)
            ?: run { this.filter = mutableSetOf(filter) } }
            ?: PageRq(filter = mutableSetOf(filter))

        return this.filterService.findAll(resRq, Action::class.java) { this.modelConverter.actionToDto(it) }
            .map { it.toPage(rq) }
    }

    override fun delete(actionId: UUID): Mono<Boolean> =
        this.actionRepository.deleteById(actionId).thenReturn(true)
}
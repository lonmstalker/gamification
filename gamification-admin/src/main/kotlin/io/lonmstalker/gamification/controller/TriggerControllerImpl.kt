package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.admin.TriggerController
import io.lonmstalker.gamification.dto.TriggerDto
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class TriggerControllerImpl : TriggerController {
    override fun createTrigger(trigger: TriggerDto): Mono<TriggerDto> {
        TODO("Not yet implemented")
    }

    override fun updateTrigger(trigger: TriggerDto, triggerId: String): Mono<TriggerDto> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flux<TriggerDto> {
        TODO("Not yet implemented")
    }

    override fun getOne(triggerId: String): Mono<TriggerDto> {
        TODO("Not yet implemented")
    }

    override fun delete(triggerId: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}
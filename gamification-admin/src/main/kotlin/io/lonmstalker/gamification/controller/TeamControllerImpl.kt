package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.admin.TeamController
import io.lonmstalker.gamification.dto.TeamDto
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class TeamControllerImpl : TeamController {
    override fun createTeam(team: TeamDto): Mono<TeamDto> {
        TODO("Not yet implemented")
    }

    override fun updateTeam(team: TeamDto, teamId: String): Mono<TeamDto> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flux<TeamDto> {
        TODO("Not yet implemented")
    }

    override fun getOne(teamId: String): Mono<TeamDto> {
        TODO("Not yet implemented")
    }

    override fun delete(teamId: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}
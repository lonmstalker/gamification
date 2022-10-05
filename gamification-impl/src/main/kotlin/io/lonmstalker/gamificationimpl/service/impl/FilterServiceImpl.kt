package io.lonmstalker.gamificationimpl.service.impl

import io.lonmstalker.gamificationapi.dto.Page
import io.lonmstalker.gamificationapi.dto.PageRq
import io.lonmstalker.gamificationimpl.service.FilterService
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class FilterServiceImpl(
//    private val r2dbcMappingContext: R2dbcMappingContext
) : FilterService {

    override fun <T> findAll(pageRq: PageRq, tClass: Class<T>): Mono<Page<T>> {
//        r2dbcMappingContext.getPersistentEntity(tClass)
        TODO("Not yet implemented")
    }
}
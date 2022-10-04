package io.lonmstalker.gamificationimpl.service

import io.lonmstalker.gamificationapi.dto.Page
import io.lonmstalker.gamificationapi.dto.PageRq
import reactor.core.publisher.Mono

interface FilterService {

    fun <T> findAll(pageRq: PageRq, tClass: Class<T>): Mono<Page<T>>
}
package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.PageRq
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import java.util.function.Function

interface FilterService {
    fun <T> findAll(pageRq: PageRq?, tClass: Class<T>): Mono<Tuple2<Long, MutableList<T>>>
    fun <T, D> findAll(pageRq: PageRq?, tClass: Class<T>, converter: Function<T, D>): Mono<Tuple2<Long, MutableList<D>>>
}
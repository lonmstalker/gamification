package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.dto.FilterRq
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.service.FilterService
import io.lonmstalker.gamification.service.constants.CommonConstants
import io.lonmstalker.gamification.service.utils.toSnakeCase
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.isEqual
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import reactor.util.function.Tuples
import java.util.function.Function

/**
 * Сервис для динамической фильтрации
 */
@Service
class FilterServiceImpl(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : FilterService {

    @Transactional
    override fun <T, D> findAll(
        pageRq: PageRq?, tClass: Class<T>, converter: Function<T, D>
    ): Mono<Tuple2<Long, MutableList<D>>> = this.findAll(pageRq, tClass)
        .map { Tuples.of(it.t1, it.t2.map { element -> converter.apply(element) }.toMutableList()) }

    @Transactional
    override fun <T> findAll(pageRq: PageRq?, tClass: Class<T>): Mono<Tuple2<Long, MutableList<T>>> {
        if (pageRq == null)
            return this.findAllWithCount(Query.empty(), tClass)

        var query = pageRq.filter?.let { filters ->
            filters.asSequence()
                .map { this.getCriteria(it) }
                .reduce { v1, v2 -> v1.and(v2) }
                .run { Query.query(this) }
        } ?: Query.empty()

        query = if (pageRq.sort != null) {
            val sort = pageRq.sort
            query.sort(Sort.by(Sort.Direction.valueOf(sort!!.direction.name), sort.fieldName.toSnakeCase()))
        } else {
            query.sort(Sort.by(Sort.Direction.DESC, CommonConstants.createdDate))
        }

        if (!pageRq.selectFields.isNullOrEmpty()) {
            query = query.columns(pageRq.selectFields!!)
        }

        val offset = if (pageRq.offset == 0) 1 else pageRq.offset
        val page = if (pageRq.page == 0) 1 else pageRq.page
        query = query.limit(offset).offset((page * offset).toLong() - offset)

        return this.findAllWithCount(query, tClass)
    }

    private fun <T> findAllWithCount(query: Query, tClass: Class<T>) = Mono.zip(
        this.r2dbcEntityTemplate.count(query, tClass),
        this.r2dbcEntityTemplate.select(query, tClass).collectList(),
    )

    private fun getCriteria(filter: FilterRq) =
        when (filter.operation) {
            FilterRq.Operation.EQUAL -> Criteria.where(filter.fieldName.toSnakeCase()).isEqual(filter.value)
            FilterRq.Operation.NOT_EQUAL -> Criteria.where(filter.fieldName.toSnakeCase()).not(filter.value)
            FilterRq.Operation.IN -> Criteria.where(filter.fieldName.toSnakeCase()).`in`(filter.value)
            FilterRq.Operation.IS_NULL -> Criteria.where(filter.fieldName.toSnakeCase()).isNull
            FilterRq.Operation.NOT_NULL -> Criteria.where(filter.fieldName.toSnakeCase()).isNotNull
        }
}
package io.lonmstalker.gamification.service.utils

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import reactor.util.function.Tuple2

fun <T> Tuple2<Long, MutableList<T>>.toPage(pageRq: PageRq?): Page<T> {
    val availableCount = this.t1
    val currentCount = this.t2.size

    val mustMinusResult = currentCount == 0
    val availablePages = pageRq?.let {
        val offset = if (it.offset == 0) 1 else it.offset
        var result = if (pageRq.page == 0) 1 else (availableCount.toDouble() / offset).toInt() + 1

        if (mustMinusResult && it.page <= result) result -= 1
        if (result == 0) 1 else result
    } ?: 1

    return Page(this.t2.size, pageRq?.page ?: 1, this.t2, availablePages, availableCount)
}

fun String.toSnakeCase(): String =
    PropertyNamingStrategies.SnakeCaseStrategy().translate(this)
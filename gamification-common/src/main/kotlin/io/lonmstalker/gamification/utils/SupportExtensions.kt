package io.lonmstalker.gamification.utils

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.lonmstalker.gamification.dto.ActionDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.dto.WalletDto
import io.lonmstalker.gamification.exception.WalletException
import io.lonmstalker.gamification.model.BalanceRpDto
import reactor.core.publisher.SynchronousSink
import reactor.util.function.Tuple2

operator fun <T, E> Tuple2<T, E>.component1() = this.t1

operator fun <T, E> Tuple2<T, E>.component2() = this.t2

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

fun validateTransfer(
    synchronousSink: SynchronousSink<*>, action: ActionDto, balance: BalanceRpDto
) = if (action.coins!! > balance.coinsAmount) {
    synchronousSink.error(WalletException("Недостаточно монет для трансфера"))
    false
} else if (action.matic!! >= balance.maticAmount) {
    synchronousSink.error(WalletException("Недостаточно MATIC для трансфера"))
    false
} else true
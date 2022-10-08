package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class ItemBuyRqDto(

    @field:Schema(description = "NFT токены, если есть id действия записываем в транзакцию то действие эту сумму, иначе берем из операции")
    val tokens: List<Int>? = null,

    @field:Schema(description = "Монеты, если есть id действия записываем в транзакцию то действие эту сумму, иначе берем из операции")
    val coins: Double? = null,
)

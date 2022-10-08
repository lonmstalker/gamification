package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class AdminRewardDto(

    @field:Schema(description = "Id кошелька")
    val walletId: String,

    @field:Schema(description = "Id операции, если нет создаем дефолтную  с nft и cons из запроса")
    val actionId: String? = null,

    @field:Schema(description = "NFT, если есть id действия записываем в транзакцию то действие эту сумму, иначе берем из операции")
    val nft: Int? = null,

    @field:Schema(description = "Монеты, если есть id действия записываем в транзакцию то действие эту сумму, иначе берем из операции")
    val coins: Int? = null,

    @field:Schema(description = "MATIC, если есть id действия записываем в транзакцию то действие эту сумму, иначе берем из операции")
    val matic: Int? = null
)

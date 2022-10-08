package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransactionRpDto(
    @field:JsonProperty("history") private val history: List<TransactionDto>
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
    data class TransactionDto(
        @field:JsonProperty("blockNumber") val blockNumber: Int, //  номер блока в блокчейне
        @field:JsonProperty("timeStamp") val timeStamp: Int, // время выполнения транзакции в секундах;
        @field:JsonProperty("contractAddress") val contractAddress: String, // адрес контракта токена, по которому совершена транзакция
        @field:JsonProperty("from") val from: String, // публичный ключ отправителя
        @field:JsonProperty("to") val to: String, // публичный ключ получателя
        @field:JsonProperty("tokenName") val tokenName: String, // наименование токена перевода
        @field:JsonProperty("tokenSymbol") val tokenSymbol: String, // символ токена перевода
        @field:JsonProperty("gasUsed") val gasUsed: Int, // комиссия на совершение транзакции
        @field:JsonProperty("confirmations")  val confirmations: Long, // количество подтверждений транзакции
        @field:JsonProperty("value") val value: Long?, // сумма перевода в wei. ДЛЯ КОИНОВ
        @field:JsonProperty("tokenId") val tokenId: String?, //  идентификатор NFT. ДЛЯ NFT
    )
}
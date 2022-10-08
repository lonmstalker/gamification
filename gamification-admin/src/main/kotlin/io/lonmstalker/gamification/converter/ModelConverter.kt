package io.lonmstalker.gamification.converter

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.dto.TransactionHistoryDto
import io.lonmstalker.gamification.dto.WalletDto
import io.lonmstalker.gamification.model.Item
import io.lonmstalker.gamification.model.TransactionHistory
import io.lonmstalker.gamification.model.Wallet
import org.springframework.stereotype.Component

@Component
class ModelConverter(
    private val objectMapper: ObjectMapper
) {

    fun walletToDto(wallet: Wallet): WalletDto =
        this.objectMapper.convertValue(wallet, WalletDto::class.java)

    fun dtoToWallet(walletDto: WalletDto): Wallet =
        this.objectMapper.convertValue(walletDto, Wallet::class.java)

    fun itemToDto(item: Item): ItemDto =
        this.objectMapper.convertValue(item, ItemDto::class.java)

    fun dtoToItem(itemDto: ItemDto): Item =
        this.objectMapper.convertValue(itemDto, Item::class.java)

    fun dtoToTransaction(transaction: TransactionHistory): TransactionHistory =
        this.objectMapper.convertValue(transaction, TransactionHistory::class.java)

    fun transactionToDto(transaction: TransactionHistory): TransactionHistoryDto =
        this.objectMapper.convertValue(transaction, TransactionHistoryDto::class.java)
}
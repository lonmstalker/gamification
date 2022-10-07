package io.lonmstalker.gamification.converter

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.dto.WalletDto
import io.lonmstalker.gamification.model.Item
import io.lonmstalker.gamification.model.Wallet
import org.springframework.stereotype.Component

@Component
class ModelConverter(
    private val objectMapper: ObjectMapper
) {

    fun walletToDto(wallet: Wallet) =
        this.objectMapper.convertValue(wallet, WalletDto::class.java)

    fun dtoToWallet(walletDto: WalletDto) =
        this.objectMapper.convertValue(walletDto, Wallet::class.java)

    fun itemToDto(item: Item) =
        this.objectMapper.convertValue(item, ItemDto::class.java)

    fun dtoToItem(itemDto: ItemDto) =
        this.objectMapper.convertValue(itemDto, Item::class.java)
}
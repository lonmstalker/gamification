package io.lonmstalker.gamificationimpl.converter

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamificationapi.dto.WalletDto
import io.lonmstalker.gamificationdb.model.Wallet
import org.springframework.stereotype.Component

@Component
class ModelConverter(
    private val objectMapper: ObjectMapper
) {

    fun walletToDto(wallet: Wallet) =
        this.objectMapper.convertValue(wallet, WalletDto::class.java)

    fun dtoToWallet(walletDto: WalletDto) =
        this.objectMapper.convertValue(walletDto, Wallet::class.java)
}
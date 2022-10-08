package io.lonmstalker.gamification.converter

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamification.dto.*
import io.lonmstalker.gamification.model.*
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

    fun dtoToNews(news: NewsDto): News =
        this.objectMapper.convertValue(news, News::class.java)

    fun newsToDto(news: News): NewsDto =
        this.objectMapper.convertValue(news, NewsDto::class.java)

    fun dtoToComment(comment: CommentDto): Comment =
        this.objectMapper.convertValue(comment, Comment::class.java)

    fun commentToDto(comment: Comment): CommentDto =
        this.objectMapper.convertValue(comment, CommentDto::class.java)
}
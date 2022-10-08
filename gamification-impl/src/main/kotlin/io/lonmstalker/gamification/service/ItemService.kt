package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.ItemDto
import reactor.core.publisher.Mono
import java.util.*

interface ItemService {
    fun createItem(item: ItemDto): Mono<ItemDto>

    fun updateItem(item: ItemDto, itemId: UUID): Mono<ItemDto>

    fun getOne(itemId: UUID): Mono<ItemDto>

    fun delete(itemId: UUID): Mono<Boolean>
}
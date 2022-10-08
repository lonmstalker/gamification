package io.lonmstalker.gamification.service

import io.lonmstalker.gamification.dto.ItemBuyRqDto
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import reactor.core.publisher.Mono
import java.util.UUID

interface MarketplaceService {

    fun findAll(pageRq: PageRq?): Mono<Page<ItemDto>>

    fun exchange(itemId: UUID, rqDto: ItemBuyRqDto): Mono<ItemDto>
}
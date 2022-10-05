package io.lonmstalker.gamificationimpl.service

import io.lonmstalker.gamificationapi.dto.ItemDto
import io.lonmstalker.gamificationapi.dto.Page
import io.lonmstalker.gamificationapi.dto.PageRq
import reactor.core.publisher.Mono
import java.util.UUID

interface MarketplaceService {

    fun getAll(pageRq: PageRq?): Mono<Page<ItemDto>>

    fun exchange(itemId: UUID, paymentType: String): Mono<ItemDto>
}
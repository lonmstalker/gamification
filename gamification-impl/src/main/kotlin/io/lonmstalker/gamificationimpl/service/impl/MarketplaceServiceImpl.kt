package io.lonmstalker.gamificationimpl.service.impl

import io.lonmstalker.gamificationapi.dto.ItemDto
import io.lonmstalker.gamificationapi.dto.Page
import io.lonmstalker.gamificationapi.dto.PageRq
import io.lonmstalker.gamificationimpl.service.MarketplaceService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class MarketplaceServiceImpl : MarketplaceService{
    override fun getAll(pageRq: PageRq?): Mono<Page<ItemDto>> {
        TODO("Not yet implemented")
    }

    override fun exchange(itemId: UUID, paymentType: String): Mono<ItemDto> {
        TODO("Not yet implemented")
    }
}
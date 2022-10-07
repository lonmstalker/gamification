package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.external.MarketplaceController
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.service.MarketplaceService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
class MarketplaceControllerImpl(
    private val marketplaceService: MarketplaceService
) : MarketplaceController {

    override fun getAll(pageRq: PageRq?): Mono<Page<ItemDto>> =
        this.marketplaceService.findAll(pageRq)

    override fun exchange(itemId: String, paymentType: String): Mono<ItemDto> =
        this.marketplaceService.exchange(UUID.fromString(itemId), paymentType)
}
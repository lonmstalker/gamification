package io.lonmstalker.gamificationimpl.controller

import io.lonmstalker.gamificationapi.controller.external.MarketplaceController
import io.lonmstalker.gamificationapi.dto.ItemDto
import io.lonmstalker.gamificationapi.dto.Page
import io.lonmstalker.gamificationapi.dto.PageRq
import io.lonmstalker.gamificationimpl.service.MarketplaceService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
class MarketplaceControllerImpl(
    private val marketplaceService: MarketplaceService
) : MarketplaceController {

    override fun getAll(pageRq: PageRq?): Mono<Page<ItemDto>> =
        this.marketplaceService.getAll(pageRq)

    override fun exchange(itemId: String, paymentType: String): Mono<ItemDto> =
        this.marketplaceService.exchange(UUID.fromString(itemId), paymentType)
}
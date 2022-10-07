package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.model.Item
import io.lonmstalker.gamification.service.FilterService
import io.lonmstalker.gamification.service.MarketplaceService
import io.lonmstalker.gamification.service.utils.toPage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.util.*

@Service
class MarketplaceServiceImpl(
    private val filterService: FilterService,
    private val modelConverter: ModelConverter,
) : MarketplaceService {

    @Transactional(readOnly = true)
    override fun findAll(pageRq: PageRq?): Mono<Page<ItemDto>> =
        this.filterService.findAll(pageRq, Item::class.java) { item -> this.modelConverter.itemToDto(item) }
            .map { it.toPage(pageRq) }

    override fun exchange(itemId: UUID, paymentType: String): Mono<ItemDto> {
        TODO("Not yet implemented")
    }
}
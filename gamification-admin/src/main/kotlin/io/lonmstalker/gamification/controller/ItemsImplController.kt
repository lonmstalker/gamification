package io.lonmstalker.gamification.controller

import io.lonmstalker.gamification.controller.admin.ItemsController
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.service.ItemService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class ItemsImplController(
    private val itemService: ItemService
) : ItemsController {

    override fun createItem(item: ItemDto): Mono<ItemDto> =
        this.itemService.createItem(item)

    override fun updateItem(item: ItemDto, itemId: String): Mono<ItemDto> =
        this.itemService.updateItem(item, UUID.fromString(itemId))

    override fun getOne(itemId: String): Mono<ItemDto> =
        this.itemService.getOne(UUID.fromString(itemId))

    override fun delete(itemId: String): Mono<Boolean> =
        this.itemService.delete(UUID.fromString(itemId))
}
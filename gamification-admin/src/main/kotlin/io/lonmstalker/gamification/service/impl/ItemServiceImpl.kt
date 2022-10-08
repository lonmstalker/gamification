package io.lonmstalker.gamification.service.impl

import io.lonmstalker.gamification.converter.ModelConverter
import io.lonmstalker.gamification.dto.ItemDto
import io.lonmstalker.gamification.model.Item
import io.lonmstalker.gamification.service.ItemService
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.util.*

@Service
class ItemServiceImpl(
    private val modelConverter: ModelConverter,
    private val itemRepository: ReactiveCrudRepository<Item, UUID>,
) : ItemService {

    @Transactional
    override fun createItem(item: ItemDto): Mono<ItemDto> =
        this.itemRepository.save(this.modelConverter.dtoToItem(item))
            .map { this.modelConverter.itemToDto(it) }

    @Transactional
    override fun updateItem(item: ItemDto, itemId: UUID): Mono<ItemDto> =
        this.itemRepository.save(this.modelConverter.dtoToItem(item).copy(itemId = itemId))
            .map { this.modelConverter.itemToDto(it) }

    @Transactional(readOnly = true)
    override fun getOne(itemId: UUID): Mono<ItemDto> =
        this.itemRepository.findById(itemId)
            .map { this.modelConverter.itemToDto(it) }

    @Transactional
    override fun delete(itemId: UUID): Mono<Boolean> =
        this.itemRepository.deleteById(itemId).thenReturn(true)
}
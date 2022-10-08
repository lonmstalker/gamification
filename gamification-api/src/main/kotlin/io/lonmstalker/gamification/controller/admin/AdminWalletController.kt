package io.lonmstalker.gamification.controller.admin

import io.lonmstalker.gamification.constants.EndpointConstants.ADMIN_WALLET_ENDPOINT
import io.lonmstalker.gamification.dto.Page
import io.lonmstalker.gamification.dto.PageRq
import io.lonmstalker.gamification.dto.TransactionHistoryDto
import io.lonmstalker.gamification.dto.WalletDto
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Tag(name = "Контроллер кошельков", description = "Контроллер для управления кошельками")
interface AdminWalletController {

    @PutMapping("$ADMIN_WALLET_ENDPOINT/{walletId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошелек после награждения",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = WalletDto::class))]
    )
    fun reward(
        @Parameter(description = "Id кошелька") @PathVariable walletId: String,
        @Parameter(description = "Id действия, за которое награждают") @RequestParam operationId: String,
    ): Mono<WalletDto>

    @GetMapping("$ADMIN_WALLET_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список кошельков",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = WalletDto::class)))]
    )
    fun getAll(): Flux<WalletDto>

    @GetMapping("$ADMIN_WALLET_ENDPOINT/{walletId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошелек",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = WalletDto::class))]
    )
    fun getOne(@Parameter(description = "Id кошелька") @PathVariable walletId: String): Mono<WalletDto>

    @DeleteMapping("$ADMIN_WALLET_ENDPOINT/{walletId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id кошелька") @PathVariable walletId: String): Mono<Boolean>

    @GetMapping(value = ["$ADMIN_WALLET_ENDPOINT/{walletId}", ADMIN_WALLET_ENDPOINT])
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "История транзакций",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getHistory(
        @RequestParam(required = false) pageRq: PageRq?,
        @Parameter(description = "Id кошелька, если ") @PathVariable(required = false) walletId: String?
    ): Mono<Page<TransactionHistoryDto>>
}
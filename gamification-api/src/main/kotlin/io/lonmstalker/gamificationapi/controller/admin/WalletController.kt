package io.lonmstalker.gamificationapi.controller.admin

import io.lonmstalker.gamificationapi.constants.EndpointConstants.ADMIN_WALLET_ENDPOINT
import io.lonmstalker.gamificationdb.model.Wallet
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
interface WalletController {

    @PutMapping("$ADMIN_WALLET_ENDPOINT/{walletId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        description = "Кошелек после награждения",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Wallet::class))]
    )
    fun reward(
        @Parameter(description = "Id действия, за которое награждают") @RequestParam operationId: String,
        @Parameter(description = "Id кошелька") @PathVariable walletId: String
    ): Mono<Wallet>

    @GetMapping("$ADMIN_WALLET_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список кошельков",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = Wallet::class)))]
    )
    fun getAll(): Flux<Wallet>

    @GetMapping("$ADMIN_WALLET_ENDPOINT{walletId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошелек",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Wallet::class))]
    )
    fun getOne(@Parameter(description = "Id кошелька") @PathVariable walletId: String): Mono<Wallet>

    @DeleteMapping("$ADMIN_WALLET_ENDPOINT{walletId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id кошелька") @PathVariable walletId: String): Mono<Boolean>
}
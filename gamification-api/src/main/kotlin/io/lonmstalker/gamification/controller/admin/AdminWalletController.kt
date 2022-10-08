package io.lonmstalker.gamification.controller.admin

import io.lonmstalker.gamification.constants.EndpointConstants.ADMIN_WALLET_ENDPOINT
import io.lonmstalker.gamification.dto.*
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

    @PostMapping(ADMIN_WALLET_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Список транзакций",
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = TransactionHistoryDto::class)))]
    )
    fun reward(@RequestBody rqBody: AdminRewardDto): Mono<MutableList<TransactionHistoryDto>>

    @PutMapping("$ADMIN_WALLET_ENDPOINT/{walletId}/change")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошелек после награждения",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = WalletDto::class))]
    )
    fun change(@RequestBody walletDto: WalletDto): Mono<WalletDto>

    @DeleteMapping("$ADMIN_WALLET_ENDPOINT/{walletId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(
        description = "Статус удаления",
        responseCode = "202",
        content = [Content(schema = Schema(implementation = Boolean::class))]
    )
    fun delete(@Parameter(description = "Id кошелька") @PathVariable walletId: String): Mono<Boolean>
}
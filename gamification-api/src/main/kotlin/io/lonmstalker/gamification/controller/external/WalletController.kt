package io.lonmstalker.gamification.controller.external

import io.lonmstalker.gamification.constants.EndpointConstants.WALLET_ENDPOINT
import io.lonmstalker.gamification.dto.*
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono
import java.util.UUID

@Tag(name = "Контроллер кошелька", description = "Контроллер для работы с кошельком")
interface WalletController {

    @GetMapping(WALLET_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошелек текущего пользователя",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = WalletDto::class))]
    )
    fun getCurrentWallet(): Mono<WalletDto>

    @PostMapping("$WALLET_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошельки коллег по команде",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getTeamWallets(
        @Parameter(description = "Тип команды, если пусто - ищет по компании") @RequestParam(required = false) teamType: TeamType?,
        @RequestBody(required = false) pageRq: PageRq?
    ): Mono<Page<WalletDto>>

    @PostMapping("$WALLET_ENDPOINT/top")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Топ кошельков коллег",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getTopWallets(@RequestBody(required = false) pageRq: PageRq?): Mono<Page<WalletDto>>

    @PostMapping("$WALLET_ENDPOINT/history/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "История транзакций",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getCurrentHistory(@RequestBody(required = false) pageRq: PageRq?): Mono<Page<TransactionHistoryDto>>

    @PostMapping("$WALLET_ENDPOINT/history/{walletId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "История транзакций коллеги",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getColleagueHistory(
        @PathVariable walletId: String, @RequestBody(required = false) pageRq: PageRq?
    ): Mono<Page<TransactionHistoryDto>>
}
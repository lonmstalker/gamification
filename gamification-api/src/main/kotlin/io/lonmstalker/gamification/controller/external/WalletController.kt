package io.lonmstalker.gamification.controller.external

import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.EndpointConstants.WALLET_ENDPOINT
import io.lonmstalker.gamification.constants.Views
import io.lonmstalker.gamification.dto.*
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

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

    @JsonView(Views.VISIBLE::class)
    @PostMapping("$WALLET_ENDPOINT/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошельки коллег по команде",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = Page::class))]
    )
    fun getTeamWallets(
        @Parameter(description = "Тип команды, если пусто - ищет по компании") @RequestParam(required = false) teamType: TeamType?,
        @RequestBody(required = false) pageRq: PageRq?,
    ): Mono<Page<WalletDto>>

    @GetMapping("$WALLET_ENDPOINT/{walletId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
        description = "Кошелек",
        responseCode = "200",
        content = [Content(schema = Schema(implementation = WalletDto::class))]
    )
    fun getOne(@Parameter(description = "Id кошелька") @PathVariable walletId: String): Mono<WalletDto>

    @JsonView(Views.VISIBLE::class)
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
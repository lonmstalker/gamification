package io.lonmstalker.gamification.exception.exception

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamification.constants.Errors.SERVER_ERROR
import io.lonmstalker.gamification.exception.BusinessException
import io.lonmstalker.gamification.model.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.LocalDateTime

@Component
class ExceptionHandler(private val objectMapper: ObjectMapper) : WebExceptionHandler {

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        exchange.response.headers.contentType = MediaType.APPLICATION_JSON
        if (ex is BusinessException) {
            return this.writeBytes(ex.message!!, HttpStatus.BAD_REQUEST, exchange)
        }
        return this.writeBytes(SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, exchange)
    }

    private fun writeBytes(message: String, status: HttpStatus, exchange: ServerWebExchange) =
        exchange.response.writeWith {
            Mono.fromCallable {
                exchange.response.statusCode = status
                val bytes: ByteArray =
                    this.objectMapper.writeValueAsBytes(ErrorDto(message, LocalDateTime.now()))
                exchange.response.writeWith(Flux.just(exchange.response.bufferFactory().wrap(bytes)))
            }
        }.subscribeOn(Schedulers.boundedElastic())
}
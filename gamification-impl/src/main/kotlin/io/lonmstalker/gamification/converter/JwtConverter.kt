package io.lonmstalker.gamification.converter

import io.lonmstalker.gamification.constants.CommonConstants
import io.lonmstalker.gamification.model.UserDto
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.oauth2.jwt.Jwt
import reactor.core.publisher.Mono
import java.util.*

class JwtConverter(
    private val authorityConverter: AuthorityConverter
) : Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    override fun convert(jwt: Jwt): Mono<AbstractAuthenticationToken> =
        Mono.fromCallable {
            UserDto(
                firstName = jwt.getClaimAsString(CommonConstants.FIRST_NAME),
                lastName = jwt.getClaimAsString(CommonConstants.LAST_NAME),
                middleName = jwt.getClaimAsString(CommonConstants.MIDDLE_NAME),
                phone = jwt.getClaimAsString(CommonConstants.PHONE),
                email = jwt.getClaimAsString(CommonConstants.EMAIL),
                userId = UUID.fromString(jwt.getClaimAsString(CommonConstants.USER_ID)),
                authoritiesCollection = authorityConverter.convert(jwt)
            )
        }
}
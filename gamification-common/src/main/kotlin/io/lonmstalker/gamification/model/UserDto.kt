package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class UserDto(
    val userId: UUID,
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val phone: String, val email: String,
    val authoritiesCollection: MutableCollection<out GrantedAuthority>,
) : AbstractAuthenticationToken(authoritiesCollection) {
    override fun getCredentials(): Any = StringUtils.EMPTY

    override fun getPrincipal(): Any = this.userId
}

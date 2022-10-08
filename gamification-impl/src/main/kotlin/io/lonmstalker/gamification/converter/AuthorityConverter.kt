package io.lonmstalker.gamification.converter

import com.nimbusds.jose.shaded.json.JSONArray
import com.nimbusds.jose.shaded.json.JSONObject
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import java.util.stream.Collectors

class AuthorityConverter : Converter<Jwt, MutableCollection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): MutableCollection<GrantedAuthority> {
        val delegate = JwtGrantedAuthoritiesConverter()
        val grantedAuthorities = delegate.convert(jwt)!!
        if (jwt.getClaim<Any>("realm_access") == null) {
            return grantedAuthorities
        }
        val realmAccess: JSONObject = jwt.getClaim("realm_access")
        if (realmAccess["roles"] == null) {
            return grantedAuthorities
        }
        val roles: JSONArray = realmAccess["roles"] as JSONArray
        val keycloakAuthorities: List<SimpleGrantedAuthority?> = roles.stream()
            .map { role -> SimpleGrantedAuthority("ROLE_$role") }
            .collect(Collectors.toList())
        grantedAuthorities.addAll(keycloakAuthorities)
        return grantedAuthorities
    }
}
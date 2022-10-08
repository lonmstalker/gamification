package io.lonmstalker.gamification.config

import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : KeycloakWebSecurityConfigurerAdapter() {

    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy = NullAuthenticatedSessionStrategy()

    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http
            .authorizeRequests()
            .antMatchers("/api/anonymous/**").permitAll()
            .anyRequest().fullyAuthenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        super.configure(auth)
        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

}
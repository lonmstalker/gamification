package io.lonmstalker.gamification.config

import io.lonmstalker.gamification.utils.UserUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity) = http
            .cors().disable()
            .csrf().disable()
            .httpBasic().disable()
            .logout().disable()
            .formLogin().disable()
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .requestCache().requestCache(NoOpServerRequestCache.getInstance())
            .and()
            .authorizeExchange()
            .anyExchange().permitAll()
            .and()
            .build()

    @Bean
    fun reactiveUserAware() = ReactiveAuditorAware { UserUtils.getCurrentUserId() }
}
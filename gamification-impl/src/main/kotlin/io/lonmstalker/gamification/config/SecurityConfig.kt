package io.lonmstalker.gamification.config

import io.lonmstalker.gamification.converter.AuthorityConverter
import io.lonmstalker.gamification.converter.JwtConverter
import io.lonmstalker.gamification.utils.UserUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity, jwtConverter: JwtConverter
    ) = http
        .cors().configurationSource(this.corsConfigurationSource())
        .and()
        .csrf().disable()
        .httpBasic().disable()
        .logout().disable()
        .formLogin().disable()
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .requestCache().requestCache(NoOpServerRequestCache.getInstance())
        .and()
        .authorizeExchange()
//        .pathMatchers("/admin/v1").hasAnyRole(Role.HR.name, Role.ADMIN.name, Role.MANAGER.name)
//        .pathMatchers("/external/v1").authenticated()
        .anyExchange().permitAll()
        .and()
        .oauth2ResourceServer { it.jwt { it.jwtAuthenticationConverter(jwtConverter) } }
        .build()

    @Bean
    fun reactiveUserAware() = ReactiveAuditorAware { UserUtils.getCurrentUserId() }

    @Bean
    fun jwtAuthenticationConverter(authorityConverter: AuthorityConverter): JwtConverter =
        JwtConverter(authorityConverter)

    @Bean
    fun jwtGrantedAuthoritiesConverter(): AuthorityConverter = AuthorityConverter()

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOriginPatterns = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}
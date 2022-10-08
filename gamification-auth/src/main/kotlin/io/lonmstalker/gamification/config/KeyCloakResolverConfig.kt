package io.lonmstalker.gamification.config

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeyCloakResolverConfig {

    @Bean
    fun KeycloakConfigResolver(): KeycloakConfigResolver = KeycloakSpringBootConfigResolver()
}
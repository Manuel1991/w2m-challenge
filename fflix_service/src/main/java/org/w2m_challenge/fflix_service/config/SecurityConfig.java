package org.w2m_challenge.fflix_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/spaceship").hasAuthority("SCOPE_read:spaceship")
                        .requestMatchers(HttpMethod.GET, "/api/spaceship/**").hasAuthority("SCOPE_read:spaceship")
                        .requestMatchers(HttpMethod.POST, "/api/spaceship/**").hasAuthority("SCOPE_create:spaceship")
                        .requestMatchers(HttpMethod.PUT, "/api/spaceship/**").hasAuthority("SCOPE_update:spaceship")
                        .requestMatchers(HttpMethod.DELETE, "/api/spaceship/**").hasAuthority("SCOPE_delete:spaceship")
                )
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .build();
    }
}

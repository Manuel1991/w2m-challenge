package org.w2m_challenge.fflix_service.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class SecurityConfigTest {
    @Bean
    public JwtDecoder jwtDecoder() {
        JwtDecoder jwtDecoder = mock(JwtDecoder.class);

        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "HS256")
                .claim("scope", "delete")
                .claim("sub", "user123")
                .build();

        when(jwtDecoder.decode(anyString())).thenReturn(jwt);

        return jwtDecoder;
    }
}

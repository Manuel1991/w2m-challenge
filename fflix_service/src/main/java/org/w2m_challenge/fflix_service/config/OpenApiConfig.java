package org.w2m_challenge.fflix_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Value("${okta.oauth2.issuer}")
    private String tokenUrl;

    @Value("${okta.oauth2.audience}")
    private String audience;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("OAuth2"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSecuritySchemes("OAuth2", getOAuth2SecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .addSecurityItem(new SecurityRequirement().addList("OAuth2"));
    }

    private SecurityScheme getOAuth2SecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(getOAuthFlow());
    }

    private OAuthFlows getOAuthFlow() {
        return new OAuthFlows().clientCredentials(getAuth0Flow());
    }

    private OAuthFlow getAuth0Flow() {
        OAuthFlow flow = new OAuthFlow();
        flow.tokenUrl(getTokenUrl());
        flow.addExtension("audience", audience);
        flow.addExtension31("audience", audience);
        return flow;
    }

    private String getTokenUrl() {
        return String.format(
                "%s/oauth/token",
                tokenUrl.replaceAll("/$", "")
        );
    }
}
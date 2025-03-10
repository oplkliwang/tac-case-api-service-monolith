package com.beaconstrategists.taccaseapiservice.config.resourcesvr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerSecurityConfig {

    /*
      This is used when running as a separate service.
     */
    @Value("${AUTH_SVC_ISSUER_URI:http://localhost:8080/oauth2/jwks}")
    private String issuerUri;

    private final JwtDecoder jwtDecoder;

    public ResourceServerSecurityConfig(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Bean(name = "ProdSecurityFilterChain")
    @Order(1)
    @ConditionalOnProperty(name = "API_SVC_ENV", havingValue = "secured", matchIfMissing = false)
    public SecurityFilterChain prodResourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // Apply security specifically for API endpoints
        http.securityMatcher("/api/**") // Match only Resource Server endpoints
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs*/**", "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAuthority("SCOPE_read.cases")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("SCOPE_write.cases")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_write.cases")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("SCOPE_write.cases")
                        //.requestMatchers("/api/**").authenticated())
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)));

        return http.build();
    }

    @Bean(name = "DevSecurityFilterChain")
    @Order(2)
//    @ConditionalOnProperty(name = "API_SVC_ENV", havingValue = "unsecured", matchIfMissing = true)
    public SecurityFilterChain devResourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // Apply security specifically for API endpoints
        http.securityMatcher("/api/**") // Match only Resource Server endpoints
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**","/v3/api-docs/**", "/v3/api-docs*/**", "/h2-console/**").permitAll()
                        .requestMatchers("/api/**").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)));

        return http.build();
    }

    /*
      This is used when running as a separate service.
     */

/*
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(issuerUri).build();
    }
*/
}

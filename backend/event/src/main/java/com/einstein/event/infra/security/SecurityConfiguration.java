package com.einstein.event.infra.security;

import com.einstein.event.services.exceptions.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final SecurityFilter securityFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    public SecurityConfiguration(CustomAccessDeniedHandler customAccessDeniedHandler, SecurityFilter securityFilter, ExceptionHandlerFilter exceptionHandlerFilter) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.securityFilter = securityFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .requestMatchers("/api/rector").hasRole("ADMIN")
                        .requestMatchers("/api/coordinator").hasAnyRole("ADMIN", "RECTOR")
                        .requestMatchers("/api/course").hasAnyRole("ADMIN", "RECTOR")
                        .requestMatchers("/api/event").hasAnyRole("ADMIN", "RECTOR", "COORDINATOR")
                        .requestMatchers("/api/presence/inscription").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers("/api/presence/event")
                        .hasAnyRole("ADMIN", "RECTOR", "COORDINATOR")
                        .requestMatchers("/api/presence/student")
                        .hasAnyRole("ADMIN", "RECTOR", "COORDINATOR", "STUDENT")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(securityFilter, ExceptionHandlerFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

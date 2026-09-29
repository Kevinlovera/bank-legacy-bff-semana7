package com.banco.bank_web_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de autenticación y autorización.
 *
 * El microservicio WEB solamente permite acceder a usuarios
 * que posean el rol WEB.
 */
@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager users(PasswordEncoder encoder) {

        UserDetails webUser = User
                .withUsername("webuser")
                .password(encoder.encode("Web123"))
                .roles("WEB")
                .build();

        return new InMemoryUserDetailsManager(webUser);
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/actuator/health",
                                "/actuator/info"
                        ).permitAll()
                        .requestMatchers("/api/web/**")
                        .hasRole("WEB")
                        .anyRequest()
                        .authenticated()
                )

                .httpBasic(httpBasic -> {});

        return http.build();
    }
}
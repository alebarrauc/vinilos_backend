package com.example.fs3_1_usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configuración de CSRF (habilitar o deshabilitar según necesidades)
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF solo para pruebas, no en producción
            // Configuración de autorizaciones
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/csrf").permitAll() // Permitir acceso público a estas rutas
                .anyRequest().authenticated() // El resto requiere autenticación
            )
            // Configuración de autenticación básica
            .httpBasic(Customizer.withDefaults());// Configuración predeterminada para HTTP Basic


        return http.build();
    }

}

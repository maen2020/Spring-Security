package com.maen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuracion numero 1 con authorizeHttpRequest casi deprecado.
    @Bean //Sirve para indicar que este bean será administrado por Spring Boot
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests() //Configurar cuales URL's van a estar permitidas o protegidas.
                    .requestMatchers("/v1/index").permitAll() //Arreglo de string de urls sin autorizacion.
                    .anyRequest().authenticated() //Cualquier otra url necesita autenticacion.
                .and()
                .formLogin().permitAll() //Permitir a todos el Login.
                .and()
                .httpBasic()
                .and()
                .build();
    }
}

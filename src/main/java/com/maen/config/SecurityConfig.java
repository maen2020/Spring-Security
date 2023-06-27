package com.maen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    // Configuracion numero 1 con authorizeHttpRequest casi deprecado.
    @Bean //Sirve para indicar que este bean será administrado por Spring Boot
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests() //Configurar cuales URL's van a estar permitidas o protegidas.
                    .requestMatchers("/v1/index2").permitAll() //Arreglo de string de urls sin autorizacion.
                    .anyRequest().authenticated() //Cualquier otra url necesita autenticacion.
                .and()
                .formLogin().permitAll() //Permitir a todos el Login.
                .and()
                .httpBasic()
                .and()
                .build();
    }*/

    /*
    /**
     * Configuracion basica #2 con funcion lambda.
     */
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/v1/index2").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin().permitAll()
                .and()
                .build();
    }*/

    /**
     * Configuraion #3 un poco mas compleja al asegurar una aplicacion o endpoints.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/v1/index2").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                    .successHandler(successHandler()) //Url a donde se va a dirigir despues de iniciar sesion.
                    .permitAll()
                .and()
                .build();
    }

    /**
     * Metodo que redirecciona a un endpoint especifico al ingresar las credenciales correctamente.
     */
    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/v1/index");
        });
    }
}
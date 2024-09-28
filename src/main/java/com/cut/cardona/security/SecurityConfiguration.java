package com.cut.cardona.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter; // Tu filtro de JWT

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configuración sin estado
            .and().authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers(HttpMethod.POST).permitAll()
            .requestMatchers(HttpMethod.GET).permitAll()
            .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN") // Solo ADMIN
            .requestMatchers("/user/**").hasRole("USER")   // Solo USER
            .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .cors(Customizer.withDefaults())
            .build();
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    //Configuracion para api
/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // Desactiva CSRF para API
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin estado para JWT
                .and()
                .authorizeHttpRequests()
                // Rutas de API
                .requestMatchers("/api/login").permitAll() // Permitir acceso a login de API
                .requestMatchers("/api/**").authenticated() // Rutas de API requieren autenticación
                // Rutas de Swagger
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                // Rutas de MVC
                .requestMatchers("/", "/home", "/about").permitAll() // Permitir acceso a páginas de inicio
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                .and()
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Agrega tu filtro
                .cors(Customizer.withDefaults()); // Configura CORS según sea necesario

        return httpSecurity.build();
    }*/
}

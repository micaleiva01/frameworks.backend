package com.frameworksbackend.trabajofinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Ensure CSRF is disabled
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Apply CORS configuration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/movies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/movies").permitAll()
                        .requestMatchers(HttpMethod.POST, "/actors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/movies/search").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/reviews/**").hasRole("USER")
                        .anyRequest().permitAll() // Temporarily allow all requests for debugging
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(username -> new org.springframework.security.core.userdetails.User(
                username,
                passwordEncoder().encode("password"),
                List.of()
        ));

        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));  // Allow all headers
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

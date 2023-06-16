package com.autoelite.AutoElite.security;

import com.autoelite.AutoElite.login.AutenticacionService;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private AutenticacionService autenticacionService;
    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers(HttpMethod.POST, "api/v1/userlogin").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/registro/**").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/registro").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "api/v1/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.GET, "api/v1/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "api/v1/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.GET, "api/v1/resumen/**").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/carro").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/publicaciones").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/publicaciones/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/publicaciones").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/publicaciones/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/publicaciones/**").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

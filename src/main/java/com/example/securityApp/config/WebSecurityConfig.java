package com.example.securityApp.config;

import com.example.securityApp.repository.CustomCsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final CustomCsrfTokenRepository csrfTokenRepository;

    @Autowired
    public WebSecurityConfig(AuthenticationProvider authenticationProvider, CustomCsrfTokenRepository csrfTokenRepository) {
        this.authenticationProvider = authenticationProvider;
        this.csrfTokenRepository = csrfTokenRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.formLogin(c -> c
                .defaultSuccessUrl("/home", true)
        );

        http.authenticationProvider(authenticationProvider);

        http.csrf(csrf -> csrf
                .csrfTokenRepository(csrfTokenRepository)
                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
        );

        http.authorizeHttpRequests(m -> m.anyRequest().permitAll());

        return http.build();
    }
}

package com.demo.dh.security.config.security;

import com.demo.dh.security.util.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    private AuthenticationProvider authenticationProvider;

    public HttpSecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(this.authenticationProvider)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/auth/public-access").permitAll();
                    authConfig.requestMatchers("/error").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/product").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.POST,"/product").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    authConfig.anyRequest().denyAll();
                })
        ;


        return httpSecurity.build();
    }
}

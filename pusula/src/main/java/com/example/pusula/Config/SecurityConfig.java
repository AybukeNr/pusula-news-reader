package com.example.pusula.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Customize the query to match your table structure without the 'enabled' column
        userDetailsManager.setUsersByUsernameQuery(
                "select username,password,1 as enabled from users where username = ?");

        userDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username = ?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthSuccessHandler authSuccessHandler) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer.requestMatchers("/authorNews").authenticated().anyRequest().permitAll())
                .formLogin(form ->
                        form.loginPage("/login").loginProcessingUrl("/auth").successHandler(authSuccessHandler).permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }
}

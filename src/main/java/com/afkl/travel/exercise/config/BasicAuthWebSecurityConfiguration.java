package com.afkl.travel.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthWebSecurityConfiguration {

    @Autowired
    private KlmBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/locations/**").hasRole("API_ROLE")
                .antMatchers(HttpMethod.GET, "/actuator/metrics/**").hasRole("METRIC_ROLE")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails apiUser = User
                .withUsername("someuser")
                .password("$2a$08$80sPB6HX0VJ790baHrd/AOc4i2D7v7c/tYQH5wTrFs8Xpoe9poBBS")
                .roles("API_ROLE")
                .build();
        UserDetails metric = User
                .withUsername("ops")
                .password("$2a$08$80sPB6HX0VJ790baHrd/AOc4i2D7v7c/tYQH5wTrFs8Xpoe9poBBS")
                .roles("METRIC_ROLE")
                .build();

        return new InMemoryUserDetailsManager(apiUser, metric);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
package com.saurabh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails saurabh = User.builder().username("saurabh").password("{noop}test123").roles("EMPLOYEE").build();

        UserDetails amit = User.builder().username("amit").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();

        UserDetails shubham = User.builder().username("shubham").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(shubham, amit, saurabh);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer.anyRequest().authenticated()).formLogin(form -> form.loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll()).logout(logout -> logout.permitAll());

        return http.build();
    }
}



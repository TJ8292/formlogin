package com.kuiteul.formlogindemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public UserDetailsManager manager() {
        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user1 = User.withUsername("Louis")
                .password("kuiteul")
                .authorities("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("Charlotte")
                .password("tchaho")
                .authorities("USER")
                .build();

        memoryUserDetailsManager.createUser(user1);
        memoryUserDetailsManager.createUser(user2);

        return memoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin().defaultSuccessUrl("/home", true);

        http.authorizeRequests()
                .mvcMatchers("/admin").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/main").permitAll()
                .anyRequest()
                .authenticated();
        return http.build();
    }
}

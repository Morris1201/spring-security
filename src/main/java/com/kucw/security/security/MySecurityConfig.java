package com.kucw.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/hello").authenticated()
                        .requestMatchers("/welcome").hasRole("ADMIN")
                        .anyRequest().denyAll()
                )
                .build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails userTest1 = User
//                .withUsername("test1")
//                .password("{noop}111")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails userTest2 = User
//                .withUsername("test2")
//                .password("{bcrypt}$2a$12$ql26r4md5sL0r.o1/CLDbe18GcBBemisvQUYq4C1U.kVgwI46f6Fi")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userTest1, userTest2);
//    }
}

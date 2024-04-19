package com.example.weddinginvitation.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
                                .requestMatchers("/member/*").permitAll()
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(login -> login
//                                .loginPage("/member/kakaoLogin")
//                                .loginProcessingUrl("/login-process")
                                .usernameParameter("email")
                                .passwordParameter("textAuthenticationNumber")
//                                .defaultSuccessUrl("/view/dashboard", true)
                                .permitAll()
                )
                .logout(withDefaults());

        return http.build();
    }

}

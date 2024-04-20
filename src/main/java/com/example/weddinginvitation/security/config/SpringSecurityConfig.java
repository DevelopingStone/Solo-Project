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
    
    /**
     * @return SpringSecurity 암호화방식
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * @param http SpringSecurity 접근허용설정 (테스트를위해 모두허용)
     * @return 규칙반환
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
//                                .requestMatchers("/member/*","/map/*","/chat","/chater.html").permitAll()
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

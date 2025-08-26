package org.iclass.spring_8secu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info(">>>>>>>>>>>> 커스텀 시큐리티 필터 체인 동작 <<<<<<<<<<<<");
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/login", "/signup", "/css/**").permitAll()
        .anyRequest().authenticated());

    http.formLogin(login -> login
        .loginPage("/login")
        .defaultSuccessUrl("/"));

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

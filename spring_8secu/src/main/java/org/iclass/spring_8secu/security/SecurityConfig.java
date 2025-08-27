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

  // 시큐리티는 필터 기능을 이용해서 요청을 처리하기 전에 특정 기능을 실행합니다.
  // ✅ 인증(아이디,패스워드) 을 위해서 필터를 사용.
  // ㄴ 로그인 해야 접근 가능한 URL 이라면 ...
  // 1) 로그인 상태 ❌ : 로그인 페이지로 이동 -> 아이디,패스워드 입력
  // (/login POST 요청) -> 시큐리티 처리(컨트롤러 필요없음). 시큐리티가 사용할 서비스는 Custom 하게 작성.
  // 2) 로그인 상태 ⭕ : 인증된 사용자 정보를 핸들러 메소드에 전달
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info(">>>>>>>>>>>> 커스텀 시큐리티 필터 체인 동작 <<<<<<<<<<<<");
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/login", "/signup", "/css/**").permitAll()
        .anyRequest().authenticated()); // 나머지 URL 은 인증 필수

    http.formLogin(login -> login
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error")); // ?error 파라미터 이름. 값은 없음

    http.logout(logout -> logout.logoutUrl("/logout")
        .logoutSuccessUrl("/?logout"));

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

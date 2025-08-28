package org.iclass.spring_9jwt.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.iclass.spring_9jwt.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	// private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final SpringDocProperties springDocProperties;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	private static String[] DEFAULT_ALLOWLIST = {
			"/", "/js/**", "/css/**", "/images/**", "/api/auth/signup", "/api/auth/login"
	};

	private String[] FINAL_ALLOWLIST = null;

	// @Value("${springdoc.swagger-ui.enabled:true}") // application.properties 일때만
	// 가능
	// private boolean swaggerUiEnabled;
	//
	// application.yml 로 하면 SpringDocProperties 에서 값을 읽어와야 합니다.
	// @Value("${springdoc.api-docs.enabled:true}")
	// private boolean apiDocsEnabled;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("=== SecurityConfig filterChain ===");

		// FormLogin, BasicHttp 비활성화
		http.formLogin(form -> form.disable()) // ✅ 세션 로그인 비활성화
				.httpBasic(basic -> basic.disable()); // ✅ 기본 인증 비활성화
		// 세션 관리 상태 없음으로 구성, 기존 아이디와 패스워드 인증으로 세션을 쿠키에 유지하는 방식을 사용하지 않음
		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS));
		// CSRF, CORS
		http.csrf(csrf -> csrf.disable()); // 토큰 인증을 할것이므로 csrf 토큰은 비활성화
		http.cors(cors -> cors.configurationSource(corsConfigurationSource())); // cors 기본 설정

		if (springDocProperties.getSwaggerUi().isEnabled() &&
				springDocProperties.getApiDocs().isEnabled()) { // if(swaggerUiEnabled && apiDocsEnabled) {

			String[] swaggerPaths = {
					"/swagger-ui/**", "/h2-console/**",
					"/swagger-ui.html",
					"/v3/api-docs/**", "/v3/api-docs",
					"/swagger-resources/**", "/swagger-resources",
					"/webjars/**",
					"/configuration/ui", "/configuration/security"
			};

			FINAL_ALLOWLIST = Stream.concat(
					Arrays.stream(DEFAULT_ALLOWLIST),
					Arrays.stream(swaggerPaths)).toArray(String[]::new);
		} else {
			FINAL_ALLOWLIST = DEFAULT_ALLOWLIST;
		}

		// 권한 규칙 작성
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(FINAL_ALLOWLIST)
				.permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated());
		//

		log.info("🔥FINAL_ALLOWLIST:{}", Arrays.toString(FINAL_ALLOWLIST));

		// JWT 필터를 UsernamePasswordAuthenticationFilter 이전에 추가
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		// 프론트 주소 등록
		config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		// Authorization 헤더 반드시 허용
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		// (필요 시) 브라우저에서 읽을 수 있도록 노출
		config.setExposedHeaders(List.of("Authorization"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
// 토큰 인증 : 리프레시 토큰 , 로그아웃 블랙리스트 , 추가적인 서버(Redis) 구성... 등의 추가적인 구현 내용이 필요합니다.
// -> 토큰 유효성 관리하는 방법들......
// -> 팀 프로젝트에서는 제외.
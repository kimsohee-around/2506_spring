package org.iclass.spring_8secu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Spring8secuApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	@DisplayName("시큐리티 패스워드 인코더 동작 결과 확인")
	void hashEncoding() {
		String result = passwordEncoder.encode("1111");
		log.info("result 1 : {} ,{}", result, result.length());

		result = passwordEncoder.encode("1111");
		log.info("result 2 : {} ,{} ", result, result.length());

		result = passwordEncoder.encode("11111111");
		log.info("result 3 : {} ,{} ", result, result.length());

	}
	/*
	 * 해시 함수 기본 :
	 * 1) 어떤 길이의 문자열이 입력되어도 인코딩 결과 길이는 똑같다.
	 * 2) 같은 문자열은 항상 동일한 결과를 만든다. (해시함수 취약점)
	 * 취약점 해결 방법으로 salting (임의의 값을 다른게 추가) => 같은 문자열도 결과가 다르게
	 * 3) 해시 알고리즘에 따라 해시 인코딩 결과 문자열은 길이가 다릅니다.
	 * 4) 일반적인 암호 알고리즘은 복호화(암호문 -> 평문) 가능합니다.
	 * 해시 함수는 일방향 함수. (암호문 -> 평문은 불가능)
	 */
}

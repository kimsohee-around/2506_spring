package org.iclass.spring_5webmvc;

import static org.junit.jupiter.api.Assertions.*;

import org.iclass.spring_3mybatis.mapper.CustomerMapper;
import org.iclass.spring_3mybatis.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
// Bean을 만들 패키지 설정
@ComponentScan(basePackages = { "org.iclass" })
// Sql 매퍼 인터페이스 찾을 패키지 설정
@MapperScan(basePackages = { "org.iclass" })
class Spring5webmvcApplicationTests {

	@Autowired
	private ProductMapper productMapper;

	// searchAll 을 중점적으로 테스트
	@Test
	void contextLoads() {
		assertNotNull(productMapper);
	}

}

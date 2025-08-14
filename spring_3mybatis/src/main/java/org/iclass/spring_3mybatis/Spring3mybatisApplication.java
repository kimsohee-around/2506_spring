package org.iclass.spring_3mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.iclass.spring_3mybatis.mapper")
@SpringBootApplication
public class Spring3mybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring3mybatisApplication.class, args);
	}

}

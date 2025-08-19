package org.iclass.spring_5webmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "org.iclass" })
public class Spring5webmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5webmvcApplication.class, args);
	}

}

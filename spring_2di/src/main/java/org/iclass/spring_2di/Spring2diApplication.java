package org.iclass.spring_2di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Spring2diApplication {

	public static void main(String[] args) {
		// context 를 이용하여 bean 저장소에 접근. bean 가져오기 할 수 있습니다.
		ApplicationContext context = SpringApplication.run(Spring2diApplication.class, args);

		// String[] beans = context.getBeanDefinitionNames(); // 빈 모두 가져오기. (이름 String
		// 을가져오기)
		// for (String bean : beans) {
		// log.info("\t bean 이름 : {}", bean);
		// }
		// log.info("현재 bean 의 갯수 : {}", String.valueOf(beans.length));
	}

}

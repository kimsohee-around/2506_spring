package org.iclass.spring_2di.component;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BuyConfig {

  @Bean(name = "foodBean")
  public List<String> list() {
    return List.of("라면", "과자", "탄산음료", "과일");
  }

  @Bean(name = "shopBean")
  public List<String> list2() {
    return List.of("이마트", "홈플러스", "GS", "CU");
  }
  // @Component 어노테이션은 개발자가 만든 클래스에만 작성을 할 수 있음.
  // 이미 만들어진 라이브러의 클래스를 Bean(객체)으로 생성하고 싶으면 메소드로 리턴.@Bean 표시.
}

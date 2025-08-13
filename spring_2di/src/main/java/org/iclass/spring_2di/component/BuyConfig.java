package org.iclass.spring_2di.component;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BuyConfig {

  @Bean
  public List<String> list() {
    return List.of("라면", "과자", "탄산음료", "과일");
  }

}

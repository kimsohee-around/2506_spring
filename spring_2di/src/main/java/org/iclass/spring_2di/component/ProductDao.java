package org.iclass.spring_2di.component;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductDao {
  private final String message = "welcome!!";

  public ProductDao() {
    log.info("ProductDao 기본생성자 - ");
  }

  public String getMessage() {
    return message;
  }

}

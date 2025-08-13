package org.iclass.spring_1demo.spring;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewDemoDao {
  private String message = "Hi,SpringBoot!!";

  public NewDemoDao() {
    log.info("NewDemoDao 생성자 - {}", this.message);
  }
}

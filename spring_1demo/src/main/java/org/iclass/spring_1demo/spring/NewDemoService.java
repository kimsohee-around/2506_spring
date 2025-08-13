package org.iclass.spring_1demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewDemoService {

  @Autowired
  private NewDemoDao dao;

  public NewDemoService() {
    log.info("NewDemoService 생성자 - ");
    // log.info("NewDemoService 생성자 - dao : {}", this.dao.getClass().toString()); //
    // this.dao 는 null
  }

}

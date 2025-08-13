package org.iclass.spring_1demo.old;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoController {
  private DemoService service;

  public DemoController() {
    this.service = new DemoService();
    log.info("DemoController 생성자 - service : {} ", this.service.getClass().toString());
  }
}

package org.iclass.spring_2di.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerService {
  private CustomerDao dao;

  @Autowired
  public void setDao(CustomerDao dao) {
    this.dao = dao;
  }

  public void test() {
    log.info("CustomerService test - dao : {}", this.dao.getClass().toString());
    dao.setGroups();
  }
}

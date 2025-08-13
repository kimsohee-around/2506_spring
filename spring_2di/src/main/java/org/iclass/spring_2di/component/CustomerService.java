package org.iclass.spring_2di.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerService {
  private CustomerDao dao;

  // => 자동 주입 없을 때 NullPointerException 확인하기 : setter 자동 실행하지 않고, dao bean 가져오기 X
  @Autowired
  public void setDao(CustomerDao dao) {
    this.dao = dao;
    log.info("setDao 실행 완료!!");
  }

  public void test() {
    dao.setGroups(); // dao 가 null 이 아니면 정상실행
    log.info("CustomerService test - dao : {}", this.dao.getClass().toString());
  }

  public void print() {
    log.info("service message : HI~");
  }
}

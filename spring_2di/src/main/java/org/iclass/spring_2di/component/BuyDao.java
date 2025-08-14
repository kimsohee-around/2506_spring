package org.iclass.spring_2di.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BuyDao {
  private List<String> sales;

  // List<String> 타입의 bean 이 존재하면 생성자 주입. 없으면 비어있는 리스트로 대입
  public BuyDao(List<String> sales) {
    // @Qualifier("shopBean") 또는 @Qualifier("foodBean") 또는 어노테이션 없음
    this.sales = sales;
    log.info("BuyDao 생성자 - sales : {}", this.sales);
  }
}

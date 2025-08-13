package org.iclass.spring_2di.component;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BuyDao {
  private List<String> sales;

  public BuyDao(List<String> sales) {
    this.sales = sales;
    log.info("BuyDao 생성자 - sales : {}", this.sales);
  }
}

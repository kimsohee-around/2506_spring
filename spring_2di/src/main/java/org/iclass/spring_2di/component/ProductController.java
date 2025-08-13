package org.iclass.spring_2di.component;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductController {
  private ProductService service;

  // @Autowired 없이 자동으로 의존관계 ProductService 타입 bean 을 가져와서 대입(할당,주입)
  // 생성자가 실행되면서 ProductService 타입의 bean 을 가져와요.그리고 this.service 에 대입(주입) 합니다.
  public ProductController(ProductService service) {
    this.service = service;
    log.info("ProductController 생성자 - service : {}", this.service.getClass().toString());
  }
  // 생성자 주입 테스트가 끝나면 @AllArgsConstructor 로 대체하기

}

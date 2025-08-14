package org.iclass.spring_4restapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.spring_4restapi.Spring4RestapiApplication;
import org.iclass.spring_4restapi.dto.CustomerDto;
import org.iclass.spring_4restapi.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @Component
@Slf4j
@AllArgsConstructor // service 초기화 생성자 + 같은 타입 Bean 을 가져와서 주입
@RestController // 응답 형식이 ResponseEntity 입니다.(상태코드와 본문(데이터))
public class CustomerRestController {

  private CustomerService service;

  @GetMapping("/api/customers")
  public ResponseEntity<List<CustomerDto>> getCustomers() {
    return ResponseEntity.ok().body(service.allCustomers());
    // service.allCustomers() 리턴되는 자바객체를 json 문자열로 변환되서 응답 body 를 만듭니다.
    // ok() 는 응답 상태 코드(200)
  }

  // 실행 예시 : http://localhost:8081/api/customers/hongGD
  // 👉hongGD는 경로변수(PathVariable) 이며 customerid 변수에 저장됩니다.
  @GetMapping("/api/customers/{customerid}")
  public ResponseEntity<CustomerDto> getCustomer(@PathVariable String customerid) {
    return ResponseEntity.ok().body(service.getCustomer(customerid));
  }

  @PostMapping("/api/customers")
  public ResponseEntity<?> save(@RequestBody CustomerDto dto) {
    // @RequestBody : 요청의 본문 json 문자열을 자바 CustomerDto 타입 객체로 변환
    // ㄴ 여러개의 값을 dto 타입으로 받을 때 필요.
    // insert 는 db에서 무결성 위반 등 오류 발생 가능성 있음.
    try {
      int result = service.join(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", result));
    } catch (Exception e) {
      log.info("save 예외 : {}", e.getMessage());
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); // 응답 body X
    }

  }

}

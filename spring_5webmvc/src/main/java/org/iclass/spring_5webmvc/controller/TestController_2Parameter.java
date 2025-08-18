package org.iclass.spring_5webmvc.controller;

import org.iclass.spring_5webmvc.TestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController_2Parameter {
  // 파라미터 : 쿼리문자열 or form 요소의 값

  @GetMapping("/list")
  public String list(String name, @RequestParam(defaultValue = "0") int age) { // 메소드의 인자는 변수명과 같은 이름 파라미터를 저장.

    log.info("파라미터 name :  {} , age :  {}", name, age);
    return "list"; // list.html
  }

  // 파라미터 4개 받기 : name, age, address, gender(String)
  @GetMapping("/listA")
  public String listA(String name, int age, String address, String gender) {

    log.info("파라미터 : {}, {}, {},{}", name, age, address, gender);
    return "list"; // list.html
  }

  @GetMapping("/listB")
  public String listB(TestDto dto) {
    log.info("파라미터 저장 dto : {}", dto);
    return "list";
  }

}

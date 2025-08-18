package org.iclass.spring_5webmvc.controller;

import org.iclass.spring_5webmvc.Spring5webmvcApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // web mvc 에서 컨트롤러 역할 @Component
public class TestController_1View {

  private final Spring5webmvcApplication spring5webmvcApplication;

  TestController_1View(Spring5webmvcApplication spring5webmvcApplication) {
    this.spring5webmvcApplication = spring5webmvcApplication;
  }

  @GetMapping("/login")
  public void login() {
    // view 파일의 이름은 자동으로 url 이름과 동일하게 찾습니다.
    // ㄴ ViewResolver 의 역할
  }

  @GetMapping("/join")
  public String register() {
    // view 파일의 이름은 join.html 로 찾아값니다.
    // ㄴ ViewResolver 의 역할
    return "join";
  }

}

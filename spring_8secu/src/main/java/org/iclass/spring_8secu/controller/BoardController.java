package org.iclass.spring_8secu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

  @GetMapping("/board/list")
  // @PreAuthorize("hasRole('ADMIN')") // role 값이 없으므로 참고만!!
  // 요청 처리 전에 권한 확인. getAuthorities() 에서 'ADMIN' 있으면 응답
  public String list(Authentication authentication) {
    log.info("username : {}", authentication.getName());
    log.info("authorities : {}", authentication.getAuthorities());
    return "board/list";
  }

}

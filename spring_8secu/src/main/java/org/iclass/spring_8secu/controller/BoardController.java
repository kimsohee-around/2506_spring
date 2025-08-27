package org.iclass.spring_8secu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

  @GetMapping("/board/list")
  public String list(Authentication authentication) {
    log.info("username : {}", authentication.getName());
    log.info("authorities : {}", authentication.getAuthorities());
    return "board/list";
  }

}

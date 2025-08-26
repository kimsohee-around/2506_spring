package org.iclass.spring_8secu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

  @GetMapping("/board/list")
  public String list() {
    return "board/list";
  }

}

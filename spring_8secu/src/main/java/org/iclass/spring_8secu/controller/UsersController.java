package org.iclass.spring_8secu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsersController {

  @GetMapping("/login")
  public String login() {
    return "login"; // login.html
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup"; // signup.html
  }
}

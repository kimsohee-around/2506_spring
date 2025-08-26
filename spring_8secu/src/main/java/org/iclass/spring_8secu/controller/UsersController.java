package org.iclass.spring_8secu.controller;

import org.iclass.spring_8secu.dto.UsersDTO;
import org.iclass.spring_8secu.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class UsersController {
  private UsersService usersService;

  @GetMapping("/login")
  public String login() {
    return "login"; // login.html
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup"; // signup.html
  }

  @PostMapping("/signup")
  public String save(UsersDTO dto) {
    log.info("dto : {}", dto);
    usersService.register(dto);
    return "redirect:/login";
  }

}

package org.iclass.spring_7jpa.controller;

import org.iclass.spring_7jpa.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class APITodoController {

  private TodoService todoService;

  //POST /api/todos
  @PostMapping("/api/todos")
  public ResponseEntity<?> 


  //GET  /api/todos/{username}

}

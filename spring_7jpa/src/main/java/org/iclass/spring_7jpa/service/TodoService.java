package org.iclass.spring_7jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.iclass.spring_7jpa.dto.TodoDto;
import org.iclass.spring_7jpa.entity.TodoEntity;
import org.iclass.spring_7jpa.repository.TodoRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TodoService {
  private TodoRepository todoRepository;

  public TodoDto write(TodoDto dto) {
    TodoEntity entity = TodoDto.toEntity(dto);
    entity = todoRepository.save(entity);
    return TodoDto.of(entity); // insert 된 값을 확인하기 위해 리턴
  }

  // 예시 : 로그인한 사용자의 Todo 를 가져오기
  public List<TodoDto> userList(String username) {
    List<TodoEntity> entityList = todoRepository.findByUsernameOrderByCreatedAtDesc(username);
    return entityList.stream().map(TodoDto::of).collect(Collectors.toList());
    // entityList 의 요소를 순서대로 하나씩 map 메소드의 인자로 전달 -> TodoDto.of(인자)
    // -> 실행 결과를 모아서 새로운 리스트 생성
  }

  public boolean validUser(String username) {
    return todoRepository.existsByUsername(username);
  }

}

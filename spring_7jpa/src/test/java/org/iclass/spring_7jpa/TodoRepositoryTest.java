package org.iclass.spring_7jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.iclass.spring_7jpa.entity.TodoEntity;
import org.iclass.spring_7jpa.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Test
  void saveOne() {
    TodoEntity entity = TodoEntity.builder()
        .title("JPA 숙제")
        .username("momo")
        .todo_date(LocalDate.of(2025, 8, 25))
        .build();
    TodoEntity saveEntity = todoRepository.save(entity);
    log.info("{}", saveEntity);
    assertNotNull(saveEntity);
  }

}

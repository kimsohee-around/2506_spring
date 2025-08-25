package org.iclass.spring_7jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.iclass.spring_7jpa.entity.TodoEntity;
import org.iclass.spring_7jpa.repository.TodoRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Test
  void selectAll() {
    List<TodoEntity> list = todoRepository.findAll();
    assertEquals(list.size(), todoRepository.count());
  }

  @Test
  void selectPaging() {
    Pageable pageable = PageRequest.of(0, 5);

    Page<TodoEntity> list = todoRepository.findAll(pageable);
    log.info("페이지 0 : {}", list);
    assertEquals(5, list.getSize());
  }

  @Test
  void test() {
    log.info("test");
  }

  @Test
  @Disabled // 테스트 안함
  void saveDummies() {
    String[] names = { "himedia", "iclass" };
    String[] todos = { "청소", "운동", "영어공부", "회의" };
    List<TodoEntity> list = new ArrayList<>();
    // for(int i=1;i<=20;i++)
    LocalDate baseTime = LocalDate.of(2025, 8, 1); // 기준날짜
    IntStream.rangeClosed(1, 20).forEach(i -> {
      TodoEntity entity = TodoEntity.builder()
          .title(todos[i % 4])
          .username(names[i % 2])
          .todo_date(baseTime.plusDays(i)) // 기준날짜 + i 일
          .checked(i % 2 == 0) // 참이면 1, 거짓이면 0
          .build();
      list.add(entity);
    });
    todoRepository.saveAll(list);
    assertEquals(21, todoRepository.count());
    // count()메소드: select count(*) from 테이블; sql 실행
  }

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

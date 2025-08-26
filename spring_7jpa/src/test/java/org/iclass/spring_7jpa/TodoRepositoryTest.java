package org.iclass.spring_7jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Test
  void selectOne() {
    // PK 로 조회하는 findById 는 리턴 타입은 Optional 입니다.
    Optional<TodoEntity> entity = todoRepository.findById(10L); // Long 타입은 10L 과 같이 표기
    if (entity.isPresent()) {
      log.info("id 조회 = 10 : {}", entity.get());
    }
    assertNotNull(entity);
  }

  @Test
  void selectAll() {
    List<TodoEntity> list = todoRepository.findAll();
    assertEquals(list.size(), todoRepository.count());
  }

  @Test
  void selectPaging() {
    Pageable pageable = PageRequest.of(2, 5,
        Sort.by("title").ascending());

    Page<TodoEntity> list = todoRepository.findAll(pageable);
    log.info("페이지 0 : {}", list);
    log.info("페이지 정보 : {},{},{}",
        list.getTotalPages(), // 전체 페이지수
        list.getContent(), // 요청 페이지의 글목록
        list.getNumber()); // 요청 페이지 번호
    assertEquals(5, list.getSize());
  }

  @Test
  void test() {
    log.info("test");
  }

  @Test
  // @Disabled // 테스트 안함
  void saveDummies() {
    todoRepository.deleteAll(); // 엔티티(테이블 행) 전체 삭제
    String[] names = { "himedia", "iclass", "momo" };
    String[] todos = { "청소", "운동", "영어공부", "회의" };
    List<TodoEntity> list = new ArrayList<>();
    // for(int i=1;i<=20;i++)
    LocalDate baseTime = LocalDate.of(2025, 8, 1); // 기준날짜
    IntStream.rangeClosed(1, 21).forEach(i -> {
      TodoEntity entity = TodoEntity.builder()
          .title(todos[i % 4])
          .username(names[i % 3])
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
        .title("JPA 숙제4")
        .username("momo4")
        .todo_date(LocalDate.of(2025, 8, 25))
        .build();
    TodoEntity saveEntity = todoRepository.save(entity);
    log.info("{}", saveEntity);
    assertNotNull(saveEntity);
  }

}

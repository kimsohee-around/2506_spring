package org.iclass.spring_9jwt;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.iclass.spring_9jwt.dto.Role;
import org.iclass.spring_9jwt.entity.BoardEntity;
import org.iclass.spring_9jwt.entity.UsersEntity;
import org.iclass.spring_9jwt.repository.BoardRepository;
import org.iclass.spring_9jwt.repository.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SampleDataTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  static List<String> users = new ArrayList<>();

  @Test
  @Order(2)
  void createBoards() {
    boardRepository.deleteAll();
    LocalDateTime baseTime = LocalDateTime.of(2025, 7, 10, 0, 0, 0);
    IntStream.rangeClosed(1, 20).forEach(i -> {
      BoardEntity board = BoardEntity.builder()
          .title("오늘의 명언 " + i)
          .content("하늘은 스스로 돕는자를 돕는다.")
          .username(users.get(i % 5))
          .build();
      boardRepository.save(board); // insert
      board.setCreatedAt(baseTime.plusDays(i + 10).plusHours(i).plusMinutes(i));
      // board.setUpdatedAt(baseTime.plusMonths(i).plusDays(i + 11)); // @PreUpdate 로
      // 동작안함.
      boardRepository.save(board); // update
    });

  }

  @Test
  @Order(1)
  void createUsers() { // 이름,
                       // 패스워드($2a$10$BW.5rjYerdxWkA3//MdCwOgUFZpW5jQIfvOsWEBOQJSwVc9ayh.DK),username,role
    userRepository.deleteAll();
    UsersEntity user = UsersEntity.builder()
        .name("홍길동").password(passwordEncoder.encode("1111"))
        .username("gd@naver.com").role(Role.USER)
        .build();
    users.add(user.getUsername());
    userRepository.save(user);

    user = UsersEntity.builder()
        .name("김모모").password(passwordEncoder.encode("1234"))
        .username("mm@gmail.com").role(Role.USER)
        .build();
    users.add(user.getUsername());
    userRepository.save(user);

    user = UsersEntity.builder()
        .name("이하니").password(passwordEncoder.encode("9999"))
        .username("honey@naver.com").role(Role.ADMIN)
        .build();
    users.add(user.getUsername());
    userRepository.save(user);
    user = UsersEntity.builder()
        .name("김나연").password(passwordEncoder.encode("1111"))
        .username("ny@daum.net").role(Role.USER)
        .build();
    users.add(user.getUsername());
    userRepository.save(user);
    user = UsersEntity.builder()
        .name("최사나").password(passwordEncoder.encode("1111"))
        .username("sana@naver.com").role(Role.USER)
        .build();
    users.add(user.getUsername());
    userRepository.save(user);
  }

}

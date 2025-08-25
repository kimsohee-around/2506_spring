package org.iclass.spring_7jpa.repository;

import java.util.List;

import org.iclass.spring_7jpa.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
  // JpaRepository<TodoEntity, Long> 에서 상속 받은 메소드는 바로 사용 가능
  // ㄴ Junit 테스트로 확인
  // 커스텀 메소드 정의
  List<TodoEntity> findByUsername(String username); // where username = ?

  List<TodoEntity> findByUsernameOrderByCreatedAtDesc(String username);

  // where username = ? order by createdAt desc
  List<TodoEntity> findByCreatedAt(LocalDateTime createdAt);

  List<TodoEntity> findByCreatedAtAfter(LocalDateTime createdAt);
  // where createAt > ?

}

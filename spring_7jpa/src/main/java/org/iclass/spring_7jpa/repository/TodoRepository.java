package org.iclass.spring_7jpa.repository;

import org.iclass.spring_7jpa.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
  // JpaRepository<TodoEntity, Long> 에서 상속 받은 메소드는 바로 사용 가능
  // ㄴ Junit 테스트로 확인
}

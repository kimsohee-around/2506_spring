package org.iclass.spring_9jwt.repository;

import java.util.List;

import org.iclass.spring_9jwt.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAllByOrderByCreatedAtDesc();
    List<BoardEntity> findByUsernameOrderByCreatedAtDesc(String username);
}
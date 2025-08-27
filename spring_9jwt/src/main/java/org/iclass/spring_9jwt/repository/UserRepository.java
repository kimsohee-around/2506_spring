package org.iclass.spring_9jwt.repository;

import java.util.Optional;

import org.iclass.spring_9jwt.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);
    boolean existsByUsername(String username);
}

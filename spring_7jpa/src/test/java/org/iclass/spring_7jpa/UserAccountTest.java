package org.iclass.spring_7jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.iclass.spring_7jpa.entity.UserAccountEntity;
import org.iclass.spring_7jpa.repository.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserAccountTest {
  @Autowired
  private UserAccountRepository accountRepository;

  @Test
  void selectByPK() {
    Optional<UserAccountEntity> result = accountRepository.findById("chchch");
    UserAccountEntity user = null;
    if (result.isPresent())
      user = result.get();
    log.info("chchch : {}", user);
    assertNotNull(user);
  }

}

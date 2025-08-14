package org.iclass.spring_3mybatis;

import static org.junit.jupiter.api.Assertions.*;

import org.iclass.spring_3mybatis.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest // 테스트 환경 설정
public class CustomerMapperTest {

  // 테스트할 bean 가져오기(필드 자동 주입)
  @Autowired
  private CustomerMapper customerMapper;

  @Test
  void mapper() {
    assertNotNull(customerMapper);
  }

  @Test
  void select() {
    log.info("{}", customerMapper.selectAll());
    assertNotEquals(0, customerMapper.selectAll().size());
  }

}

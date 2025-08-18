package org.iclass.spring_4restapi.service;

import java.util.List;

import org.iclass.spring_4restapi.dto.CustomerDto;
import org.iclass.spring_4restapi.mapper.CustomerMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Component
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class CustomerService {
  private CustomerMapper mapper; // dao

  // 비즈니스 로직 : 업무관련 처리 - 회원 등록, 회원탈퇴, 회원정보 수정, 회원조회, 이메일 중복검사...
  // 복잡한 업무 로직은 2개 이상의 DML 명령어를 실행할 수 있습니다. -> 트랜잭션 처리. 오류가 발생하면 모든 단위SQL 롤백.

  public int join(CustomerDto dto) {
    return mapper.insert(dto);
  }

  public List<CustomerDto> allCustomers() {
    return mapper.selectAll();
  }

  public CustomerDto getCustomer(String customerid) {
    return mapper.selectByPk(customerid);
  }

  // email 과 age 를 모두 변경한다.
  public int changeInfo(CustomerDto dto) {
    // 앞으로 메소드이름은 특별한 경우 제외하고 mapper 메소드 이름으로 하겠습니다.
    return mapper.update(dto);
  }

  // customerid 를 삭제한다.
  public int removeCustomer(String customerid) {
    return mapper.delete(customerid);
  }

}

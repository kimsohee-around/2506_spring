package org.iclass.spring_4restapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.spring_4restapi.dto.CustomerDto;

// @Component    // 이 어노테이션은 bean 은 만들지만 sql 매퍼 구현체는 아닙니다.
@Mapper // dao 역할을 하는 인터페이스
public interface CustomerMapper {
  CustomerDto selectByPk(String customerid);

  List<CustomerDto> selectAll();

  int insert(CustomerDto dto);

  int update(CustomerDto dto);

  int delete(String customerid);
}

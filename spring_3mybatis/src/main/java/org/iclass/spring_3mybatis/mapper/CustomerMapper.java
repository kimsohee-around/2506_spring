package org.iclass.spring_3mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.spring_3mybatis.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public interface CustomerMapper {
  CustomerDto selectByPk(String customerid);

  List<CustomerDto> selectAll();

  int insert(CustomerDto dto);

  int update(CustomerDto dto);

  int delete(String customerid);
}

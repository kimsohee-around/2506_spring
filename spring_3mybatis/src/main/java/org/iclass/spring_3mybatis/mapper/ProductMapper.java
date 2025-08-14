package org.iclass.spring_3mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.spring_3mybatis.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
  ProductDto selectByPk(String pcode);

  List<ProductDto> selectByKeywor(String keyword);

  int insert(ProductDto dto);

  int update(ProductDto dot);

  int delete(String pcode);
}

package org.iclass.spring_3mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.spring_3mybatis.dto.ProductDto;
import org.iclass.spring_3mybatis.dto.ProductSearchDto;

@Mapper // 설정의 xml 파일로 구현체 정의합니다.-> 빈 생성
public interface ProductMapper {
  ProductDto selectByPk(String pcode);

  List<ProductDto> selectByKeyword(String keyword);

  List<ProductDto> selectAll();

  int insert(ProductDto dto);

  int update(ProductDto dot);

  int delete(String pcode);

  // 검색 필드값 저장한 SearchDto 를 SQL 에 전달
  List<ProductDto> searchAll(ProductSearchDto dto);
}

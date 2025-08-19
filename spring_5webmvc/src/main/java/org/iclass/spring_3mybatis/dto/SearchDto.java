package org.iclass.spring_3mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchDto {
  // 검색 그룹안에 있는 항목으로 필드 정의
  private String pname;
  private String pcode;
  private Integer minPrice;
  private Integer maxPrice;

}

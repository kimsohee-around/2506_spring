package org.iclass.spring_4restapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public final class ProductDto { // final 클래스 : extend 할 수 없는 클래스.상속 안됨.
  // final 변수 : 초기화 이후에 값을 변경할 수 없음.
  private final String pcode;
  private final String category;
  private final String pname;
  private final int price;
}
// final 키워드는 마이바티스와 상관 없습니다. 필요시 작성.
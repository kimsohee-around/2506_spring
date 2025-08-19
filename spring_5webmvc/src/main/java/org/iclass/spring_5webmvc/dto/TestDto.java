package org.iclass.spring_5webmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor // 커스텀 생성자 초기화
@Builder // 빌더객체로 필요한 변수(필드)만 초기화
public class TestDto {
  private String name;
  private int age;
  private String address;
  private String gender;
}

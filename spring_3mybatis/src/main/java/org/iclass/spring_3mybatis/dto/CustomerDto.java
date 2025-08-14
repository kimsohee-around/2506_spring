package org.iclass.spring_3mybatis.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor // final 키워드 변수만 초기화 생성자
public class CustomerDto {
    private final String customerId;
    private final String name;
    private final String email;
    private final int age;
    private final Date regDate;
}
// 과거 마이바티스 3.4 이전 버전은 getter,setter,기본생성자 없으면 오류 발생.
// -> 현재 버전은 문제 없이 정상 실행.

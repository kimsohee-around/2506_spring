package org.iclass.spring_3mybatis.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder // 커스텀 생성자 대신에 사용.
@Getter
@Setter
@ToString
@RequiredArgsConstructor // final 키워드 변수만 초기화 생성자
public class CustomerDto {
    private final String customerId;
    private final String name;
    private final String email;
    private final Integer age; // int 로 하면 안되는 경우 : null 값이 있을 때(int 에 저장 못합니다.)
    private final Date regDate;
}
/*
 * 자바의 날짜 타입은
 * 자바 8 버전 이전 : java.util.Date
 * -> 타임리프 날짜 출력 기능의 객체를 #dates 유틸리티로 사용
 * 자바 8 버전 이후 : java.time.LocalDate, LocalTime
 * -> 타임리프 날짜 출력 기능의 객체를 #temporals 유틸리티로 사용
 */

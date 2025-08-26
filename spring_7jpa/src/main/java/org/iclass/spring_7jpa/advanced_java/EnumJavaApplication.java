package org.iclass.spring_7jpa.advanced_java;

import org.springframework.http.HttpStatus;

public class EnumJavaApplication {

  public static void main(String[] args) {
    System.out.println("enum 테스트");
    Day today = Day.MONDAY;
    System.out.println("today : " + today); // "MONDAY" 출력 => 열거형의 재정의된 today.toString() 실행
    // toString() 처럼 재정의 못함.
    System.out.println("string 으로 바꾸면 ? " + today.name());    // "MONDAY" 출력
    System.out.println("Day values : " + Day.values()); // 우리가 정의한 상수를 모두 리스트로 반환
    System.out.println("---- 모든 Day 출력하기 ----");
    for (Day d : Day.values()) {
      System.out.println(d);
    }
    System.out.println("\nDay2 enum 테스트");
    Day2 today2 = Day2.WEDNESDAY;

    System.out.println(today2.getDayNum()); // 3
    System.out.println(today2.getKoreanName()); // 수요일

    System.out.println("값 4 의 요일은 ? " + Day2.fromNumber(4));
    System.out.println("값 8 의 요일은 ? " + Day2.fromNumber(8));

    System.out.println("\n자바의 열거형 예시");
    System.out.println(HttpStatus.CREATED); // Rest APi 응답 코드 전달했던 상수
  }
}
/*
 * enum은 *enumeration(열거형)*의 줄임말로,
 * 서로 관련된 '상수들의 집합'을 이름 있는 '타입으로 정의'할 때 사용하는 자료형입니다.
 * 즉, 의미 있는 이름을 가진 "값 목록"을 정의.
 * 상수들만 모여있는 클래스(생성자,메소드 정의)
 * => 활용) 스프링 시큐리티에서 사용자의 역할(권한) 설정할 때
 * ==> HttpStatus.CREATED 는 응답코드를 201 로 보내주는 값
 */
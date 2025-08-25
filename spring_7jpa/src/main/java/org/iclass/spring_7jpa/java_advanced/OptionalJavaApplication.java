package org.iclass.spring_7jpa.java_advanced;

import java.util.Optional;

public class OptionalJavaApplication {

  public static void main(String[] args) {

    Optional<String> noneEmpty = Optional.of("HI STREAM"); // null 이 아닌 값을 저장하는 컨테이너 Optional 생성
    Optional<String> nullable = Optional.ofNullable(null); // null 을 갖을 수 있는 컨테이너 Optional 생성
    Optional<String> empty = Optional.empty(); // 비어있는 컨테이너 Optional 생성

    // 메소드 테스트
    // 1) 값의 존재 확인
    System.out.println("noneEmpty.isPresent() : ");
    System.out.println(noneEmpty.isPresent()); // 참
    System.out.println("nullable.isEmpty() : ");
    System.out.println(nullable.isEmpty()); // 참

    // 2) 값 가져오기
    System.out.println("noneEmpty.get() : ");
    System.out.println(noneEmpty.get());

    System.out.println("empty.get()");
    // System.out.println(empty.get()); // isPresent() 거짓일 때 예외 발생

    System.out.println("nullable.get() : ");
    // System.out.println(nullable.get()); // isPresent() 거짓일 때 예외 발생

    // 3) isEmpty() 일 때 처리 메소드
    String result = nullable.orElse("기본값"); // orElse 메소드는 인자는 값(T 타입)
    System.out.println(" nullable.orElse(\"기본값\") 리턴 : ");
    System.out.println(result);

    result = nullable.orElseGet(() -> "리턴값"); // orElseGet 메소드 인자가 Supplier 함수
    System.out.println("nullable.orElseGet(()-> \"리턴값\")");
    System.out.println(result);

    result = nullable.orElseThrow(); // 값이 없으면 예외 발생
  }
}

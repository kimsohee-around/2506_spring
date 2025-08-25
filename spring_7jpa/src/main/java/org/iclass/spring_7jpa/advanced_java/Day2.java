package org.iclass.spring_7jpa.advanced_java;

public enum Day2 {
  // 상수 이름에 추가적으로 정수, 문자열 값을 각각 사용할 수 있습니다.
  MONDAY(1, "월요일"),
  TUESDAY(2, "화요일"),
  WEDNESDAY(3, "수요일"),
  THURSDAY(4, "목요일"),
  FRIDAY(5, "금요일"),
  SATURDAY(6, "토요일"),
  SUNDAY(7, "일요일");

  private final int dayNum;
  private final String koreanName;

  Day2(int dayNum, String koreanName) {
    this.dayNum = dayNum;
    this.koreanName = koreanName;
  }

  public int getDayNum() {
    return dayNum;
  }

  public String getKoreanName() {
    return koreanName;
  }

  // 특정 번호로 Day 찾기
  public static Day2 fromNumber(int number) {
    for (Day2 d : Day2.values()) {
      if (d.getDayNum() == number) {
        return d;
      }
    }
    throw new IllegalArgumentException("해당 번호의 요일이 없습니다: " + number);
  }

}

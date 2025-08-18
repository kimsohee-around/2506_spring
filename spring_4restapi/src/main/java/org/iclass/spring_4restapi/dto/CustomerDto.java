package org.iclass.spring_4restapi.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    // dto 입력값의 유효성 패턴과 값 설정. 검사
    @NotBlank(message = "customerId 는 필수값 입니다.") 
    @Pattern(regexp = "^[A-Za-z0-9]{4,}$", message = "영문자와 숫자로 구성된 4글자 이상이어야 합니다.")
    private final String customerId;

    @NotBlank(message = "이름은 필수값 입니다.")
    private final String name;

    @Email
    private final String email;

    @Min(value = 0, message = "나이는 0 이상 입니다.")
    @Max(value = 100, message = "나이는 100 이하 입니다.")
    private final Integer age; // int 로 하면 안되는 경우 : null 값이 있을 때(int 에 저장 못합니다.)
    private final Date regDate;
}

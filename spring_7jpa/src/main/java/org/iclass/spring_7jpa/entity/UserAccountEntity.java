package org.iclass.spring_7jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "user_account")
@Entity
public class UserAccountEntity {

  @Id
  private String userid;

  @Column(nullable = false)
  private String username;

  private String password;

  private LocalDate birth;

  @Enumerated(EnumType.STRING) // 열거형 타입 상수이름으로 문자열 처리
  private Gender gender;

  private String email;

  private LocalDate regdate;

  @PrePersist
  public void createRegDate() {
    this.regdate = LocalDate.now();
  }

}

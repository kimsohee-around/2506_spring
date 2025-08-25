package org.iclass.spring_7jpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@AllArgsConstructor
@Builder
@ToString
@Table(name = "todo_test") // 따로 설정하지 않으면 테이블명은 TODOENTITY 입니다.
@Entity
public class TodoEntity {

  @Id // Pk
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Pk 의 정수값 생성 방법(전략)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String username;

  @Column(name = "done")
  private boolean checked;

  @Column(nullable = false)
  private LocalDate todo_date;

  private LocalDateTime createdAt;

  @PrePersist // sql insert 전에 컬럼 값을 먼저 저장
  public void createDateAndChecked() {
    this.createdAt = LocalDateTime.now(); // 현재 날짜와 시간
    this.checked = false;
  }

}

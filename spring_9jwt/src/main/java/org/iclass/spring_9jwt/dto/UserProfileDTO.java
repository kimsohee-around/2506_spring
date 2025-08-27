package org.iclass.spring_9jwt.dto;

import java.time.LocalDate;

import org.iclass.spring_9jwt.entity.UsersEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 회원가입, 로그인 요청 DTO
public class UserProfileDTO {
  private String name;
  private String username;
  private String password;
  private String nickname;
  private LocalDate birth;
  
  public static UsersEntity toEntity(UserProfileDTO dto) {
	  return UsersEntity.builder()
			  .name(dto.getName())
			  .username(dto.getUsername())
			  .password(dto.getPassword())
			  .nickname(dto.getNickname())
			  .birth(dto.getBirth())
			  .build();
  }
  
}

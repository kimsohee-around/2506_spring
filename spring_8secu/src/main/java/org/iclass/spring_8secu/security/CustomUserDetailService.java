package org.iclass.spring_8secu.security;

import org.iclass.spring_8secu.entity.UsersEntity;
import org.iclass.spring_8secu.repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

  private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsersEntity user = usersRepository.findByUsername(username);
    // username 으로 테이블에서 조회를 합니다.
    // 시큐리티 User 객체를 리턴하는 방법으로 합니다. 인증정보(계정이름,패스워드,ROLE) 저장합니다.
    return User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .build(); // 테이블의 값을 User 객체에 저장하면 /login Post 요청 처리 필터가 사용자 입력 패스워드와 비교를 합니다.
    // 일치하면 User 타입의 객체를 리턴. 일치하지 않으면 /login GET 리다이렉트
  }

}

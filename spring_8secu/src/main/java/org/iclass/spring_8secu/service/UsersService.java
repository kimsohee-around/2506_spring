package org.iclass.spring_8secu.service;

import org.iclass.spring_8secu.dto.UsersDTO;
import org.iclass.spring_8secu.entity.UsersEntity;
import org.iclass.spring_8secu.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class UsersService {
  private UsersRepository usersRepository;

  // 시큐리티가 만든 bean : 패스워드 인코딩(해쉬함수로 생성하는 암호)
  private PasswordEncoder passwordEncoder;

  public void register(UsersDTO dto) {
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    UsersEntity entity = UsersDTO.toEntity(dto);
    usersRepository.save(entity);
    log.info("회원 가입 후 id : {}", entity.getId());
  }

}

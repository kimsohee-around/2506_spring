package org.iclass.spring_9jwt.service;

import org.iclass.spring_9jwt.dto.UserProfileDTO;
import org.iclass.spring_9jwt.entity.UsersEntity;
import org.iclass.spring_9jwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class UsersService {
	
	 private final UserRepository userRepository;
	 private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UsersEntity getByCredentials(final String username,
            final String password, final PasswordEncoder encoder) {

		UsersEntity user = userRepository.findByUsername(username);
		if(user !=null && encoder.matches(password, user.getPassword())) {
			return user;
		}
		
		return null;
	} 
	
	
 // 새로운 사용자 등록
    //이 메소드에서 UserEntity 파라미터 객체의 변수를 수정할 수 없음
    public void createUser(UserProfileDTO dto) {
       String username = dto.getUsername();

       if(userRepository.existsByUsername(username)) {
            log.warn("이미 존재하는 username : {}",username);
            throw new RuntimeException("Username already exist.");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(UserProfileDTO.toEntity(dto));
    }
}

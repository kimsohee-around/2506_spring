package org.iclass.spring_9jwt.controller;

import java.util.Map;

import org.iclass.spring_9jwt.dto.UserProfileDTO;
import org.iclass.spring_9jwt.security.JwtTokenProvider;
import org.iclass.spring_9jwt.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class APIUserController {
	 private final UsersService userService;
	    private final JwtTokenProvider tokenProvider;

	    

	    @PostMapping("/signup")
	    public ResponseEntity<?> register(@RequestBody UserProfileDTO userDTO) {
	        try{
	            log.info("user DTO : {}", userDTO);
	            if(userDTO == null || userDTO.getPassword() == null){
	                throw new RuntimeException("Invalid Password.");
	            }

	            userService.createUser(userDTO);
	          
	            return ResponseEntity.ok(Map.of("success",userDTO.getUsername()));
	        }catch (Exception e){
	            
	            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
	        }
	    }


//	    @PostMapping("/login")
//	    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
//	        UsersEntity user = userService.getByCredentials(loginDto.getUsername(), loginDto.getPassword(),passwordEncoder);
//	        if(user !=null){   //아이디,패스워드 확인 성공하면
//
//	            // 로그인 토큰을 만든다.
//	            final String token = tokenProvider.generateToken(user);
//	            log.info("token : {}", token);
//	            // 토큰값을 dto 에 저장한다.
//	            final LoginDto responseLogin = LoginDto.builder()
//	                    .username(user.getUsername())
//	                    .id(user.getId())
//	                    .token(token)  //추가
//	                    .build();
//	            // 응답으로 dto를 보내준다.
//	            return ResponseEntity.ok(responseLogin);
//	        }else{
//	            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
//	                    .error("Login Failed")
//	                    .build();
//
//	            return ResponseEntity.badRequest().body(responseDTO);
//	        }
//	    }
}


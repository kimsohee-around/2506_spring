package org.iclass.board.service;

import java.util.HashMap;
import java.util.Map;

import org.iclass.board.dto.UserAccount;
import org.iclass.board.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserAccountService {
	private UserAccountMapper mapper;

	public UserAccount login(String userid, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("password", password);
		return mapper.selectForLogin(map);
	}
	
	public int join(UserAccount dto) {
		return mapper.insert(dto);
	}
	
	
	

}

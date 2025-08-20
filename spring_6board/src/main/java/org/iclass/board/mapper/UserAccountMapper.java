package org.iclass.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.UserAccount;


@Mapper
public interface UserAccountMapper {
		int insert(UserAccount dto);
		UserAccount selectForLogin(Map<String,String> map);
}

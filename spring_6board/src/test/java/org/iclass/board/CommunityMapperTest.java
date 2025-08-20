package org.iclass.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.mapper.CommunityMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@SpringBootTest
class CommunityMapperTest {

	@Autowired // 빈 저장소에서 자동주입(필드 주입)
	private CommunityMapper mapper;

	@Test
	void selectAll() {

		List<CommunityDTO> list = mapper.selectAll();
		assertNotNull(list);
	}

	@Test // 테스트할 메소드 표시
	@DisplayName("게시판 테이블에 insert 확인")
	void test() {
		CommunityDTO dto = CommunityDTO.builder()
				.title("Junit")
				.content("테스트프레임웍크")
				.writer("wonder")
				.build();
		int result = mapper.insert(dto); // 정상실행하면 1개행 추가. 값 1 리턴
		log.info("insert dto : {}", dto);
		assertEquals(1, result); // import static org.junit.jupiter.api.Assertions.*; 확인
	}

	@Test
	void delete() {
		int result = mapper.delete(2);
		assertEquals(1, result);
		CommunityDTO dto = mapper.selectByIdx(1);
		assertNull(dto);
	}

}

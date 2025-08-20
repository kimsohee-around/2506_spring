package org.iclass.board.service;

import java.util.List;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.mapper.CommunityMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CommunityService {
	private CommunityMapper mapper;

	public List<CommunityDTO> selectAll() {
		return mapper.selectAll();
	}

	public CommunityDTO read(long idx, boolean readCount) {
		if (readCount)
			mapper.setReadCount(idx); // 조회수 증가
		return mapper.selectByIdx(idx);
	}

	public void write(CommunityDTO dto) {
		mapper.insert(dto);
	}

	// 글 수정
	public void save(CommunityDTO dto) {
		mapper.update(dto);
	}

	// 글 삭제
	public void remove(int idx) {
		mapper.delete(idx);
	}

}

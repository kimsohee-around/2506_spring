package org.iclass.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageResponseDTO;
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

	public PageResponseDTO getPageList(int currentPage) {
		Map<String, Integer> map = new HashMap<>();
		// map.put("startNo", 1); map.put("endNo", 10);// 1페이지 글의 행번호
		int pageSize = 10; // 한 페이지에 글 몇개인지 설정 변수
		int startNo = (currentPage - 1) * pageSize + 1;
		int endNo = startNo + (pageSize - 1);
		map.put("startNo", startNo);
		map.put("endNo", endNo);

		int totalCount = mapper.getAllCount();
		// 페이지 개수 공식
		double temp = (double) totalCount / pageSize; // 실수값 -> 297/10 = 29.7
		int totalPages = (int) Math.ceil(temp); // 반올림해서 정수로 29.7을 정수로 반올림 30.0

		// 현재 페이지를 기준으로 페이지목록(10개 페이지 지정) 시작번호
		// currentPage 가 1~10 , 11~20 , 21~30 은 각각 모두 같은 startPage 가 계산됩니다.
		int startPage = (currentPage - 1) / 10 * 10 + 1; // 21
		int endPage = startPage + (10 - 1); // 30
		endPage = Math.min(totalPages, endPage); // endPage 는 totalPages 보다 크면 안됩니다.

		PageResponseDTO responseDTO = PageResponseDTO.builder()
				.totalCount(totalCount)
				.totalPages(totalPages)
				.startPage(startPage)
				.endPage(endPage)
				.list(mapper.selectPageList(map))
				.build();

		return responseDTO;
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

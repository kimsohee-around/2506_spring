package org.iclass.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResponseDTO {
	// private int currentPage; // 현재 페이지 추가할 수 있음.
	private int totalCount; // 전체 글 개수
	private int totalPages; // 전체 페이지 개수

	// 요청 페이지 기준으로 페이지 목록의 시작,끝 번호
	private int startPage;
	private int endPage;

	private List<CommunityDTO> list; // 요청 페이지의 글목록

}

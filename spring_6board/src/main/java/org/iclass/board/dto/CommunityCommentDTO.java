package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityCommentDTO {
	private int idx;
	private int mref;
	private String writer;
	private String content;
//	private Date createdAt;   // 문자열 변환 출력 yyyy-MM-dd
	private String regDate;
	private String ip;
	
	
	
}

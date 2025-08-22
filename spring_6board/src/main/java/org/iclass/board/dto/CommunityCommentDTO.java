package org.iclass.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

	// 유효성 검사 내용을 작성
	@NotBlank(message = "글 내용은 최소 5글자 이상입니다.")
	@Size(min = 5, message = "글 내용은 최소 5글자 이상입니다.")
	private String content;
	// private Date createdAt; // 문자열 변환 출력 yyyy-MM-dd
	private String regDate;
	private String ip;

}

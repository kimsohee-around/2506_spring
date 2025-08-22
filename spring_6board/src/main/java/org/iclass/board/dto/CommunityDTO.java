package org.iclass.board.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
	private long r; // r은 rownum 시스템 컬럼 값
	private long idx;
	private String writer;
	private String title;
	private String content;
	private int readCount;
	private Date createdAt;
	private String ip;
	private String commentCount;
}

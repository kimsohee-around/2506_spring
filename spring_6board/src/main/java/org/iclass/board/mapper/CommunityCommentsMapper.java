package org.iclass.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.CommunityCommentDTO;

@Mapper
public interface CommunityCommentsMapper {
	CommunityCommentDTO selectOneByIdx(int idx);

	int insert(CommunityCommentDTO dto);

	int delete(int idx);

	List<CommunityCommentDTO> selectCommentList(int mref);

	int selectCommentCount(int mref); // 메인글의 commentCount 컬럼이 없으면 조회할 때 필요-지금은 사용 ❌

	int updateCommentCount(int mref); // 메인글의 commentCount 컬럼을 업데이트(트랜잭션 예시)
}

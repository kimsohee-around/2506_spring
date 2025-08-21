package org.iclass.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.CommunityDTO;

@Mapper
public interface CommunityMapper {
	int getAllCount();

	List<CommunityDTO> selectAll();

	List<CommunityDTO> selectPageList(Map<String, Integer> map);

	int insert(CommunityDTO dto);

	CommunityDTO selectByIdx(long idx);

	int setReadCount(long idx);

	int update(CommunityDTO dto);

	int delete(long idx);
}

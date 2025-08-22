package org.iclass.board.service;

import java.util.List;

import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.mapper.CommunityCommentsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class CommentsService {
  private CommunityCommentsMapper commentsMapper;

  // 댓글 추가 + 댓글 갯수 업데이트 : 2개의 sql 실행이 정상적으로 실행되지 않으면 롤백
  @Transactional // class 레벨로 정의 할 수 있음.
  public int commentSave(CommunityCommentDTO dto) {
    int result = commentsMapper.insert(dto); // sql insert
    commentsMapper.updateCommentCount(dto.getMref()); // sql update
    return result;
  }

  // 댓글 삭제(pk idx) + 댓글 갯수 업데이트(mref)
  @Transactional
  public int commentDelete(int idx, int mref) { // 삭제할 댓글의 pk
    int result = commentsMapper.delete(idx);
    commentsMapper.updateCommentCount(mref);
    return result;
  }

  // mref 의 댓글 목록 가져오기
  public List<CommunityCommentDTO> commentList(int mref) {
    return commentsMapper.selectCommentList(mref);
  }

}

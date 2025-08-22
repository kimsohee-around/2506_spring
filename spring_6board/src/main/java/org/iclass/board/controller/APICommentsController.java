package org.iclass.board.controller;

import java.util.List;
import java.util.Map;

import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.service.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class APICommentsController {
  /*
   * URL (EndPoint)
   * /api/comments -> 댓글 추가
   * /api/comments/{idx} -> 댓글 삭제
   * /api/comments/{mref} -> 메인글 댓글 목록
   */

  private CommentsService commentsService;

  @PostMapping("/api/comments")
  public ResponseEntity<?> commentSave(@RequestBody CommunityCommentDTO dto) {
    int result = commentsService.commentSave(dto);
    // 잘못된 값으로 예외가 생기면 Exception 처리하는 코드가 필요.(@Valid, dto 에 검증할 내용 작성)
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", result));
  }

  @DeleteMapping("/api/comments/{idx}")
  public ResponseEntity<?> commentDelete(@PathVariable int idx) {
    int result = commentsService.commentDelete(idx);
    // idx 를 없는 값을 처리한다면 result 는 0. 없는 댓글 삭제

    if (result == 1)
      return ResponseEntity.ok().body(Map.of("success", result));
    else
      return ResponseEntity.badRequest().body(Map.of("no content", result));
  }

  @GetMapping("/api/comments/{mref}")
  public ResponseEntity<List<CommunityCommentDTO>> commentsList(@PathVariable int mref) {
    return ResponseEntity.ok().body(commentsService.commentList(mref));
  }

}

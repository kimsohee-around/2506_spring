package org.iclass.spring_9jwt.controller;

import java.util.List;

import org.iclass.spring_9jwt.dto.BoardRequest;
import org.iclass.spring_9jwt.dto.BoardResponse;
import org.iclass.spring_9jwt.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    // 권한 OR. 권한이 여러개 설정한 경우 hasRole('ADMIN') and hasRole('USER') 와 같이 and 조건 가능
    public ResponseEntity<List<BoardResponse>> getAllBoards(
            Authentication authentication, // 사용자 인증 정보
            @RequestParam(name = "me", required = false) String me // 추가 파라미터
    ) {
        List<BoardResponse> boards = null;
        if (me == null)
            boards = boardService.getAllBoards();
        else
            boards = boardService.getMyBoards(authentication.getName()); // 인증 객체에서 username 가져오기
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<BoardResponse> getBoard(
            @Parameter @PathVariable("id") Long id) {
        BoardResponse board = boardService.getBoard(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BoardResponse> createBoard(
            @RequestBody @Valid BoardRequest request,
            Authentication authentication) {

        String userEmail = authentication.getName();
        BoardResponse board = boardService.createBoard(request, userEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BoardResponse> updateBoard(
            @Parameter @PathVariable("id") Long id,
            @RequestBody @Valid BoardRequest request,
            Authentication authentication) {

        String userEmail = authentication.getName();
        BoardResponse board = boardService.updateBoard(id, request, userEmail);

        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // ADMIN 권한 테스트용
    public ResponseEntity<Void> deleteBoard(
            @Parameter @PathVariable("id") Long id,
            Authentication authentication) {

        String userEmail = authentication.getName();
        boardService.deleteBoard(id, userEmail);

        return ResponseEntity.noContent().build();
    }
}
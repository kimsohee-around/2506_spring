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
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<BoardResponse> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long id) {
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
            @PathVariable Long id,
            @RequestBody @Valid BoardRequest request,
            Authentication authentication) {
        
        String userEmail = authentication.getName();
        BoardResponse board = boardService.updateBoard(id, request, userEmail);
        
        return ResponseEntity.ok(board);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id,
            Authentication authentication) {
        
        String userEmail = authentication.getName();
        boardService.deleteBoard(id, userEmail);
        
        return ResponseEntity.noContent().build();
    }
}
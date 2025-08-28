package org.iclass.spring_9jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.iclass.spring_9jwt.dto.BoardRequest;
import org.iclass.spring_9jwt.dto.BoardResponse;
import org.iclass.spring_9jwt.entity.BoardEntity;
import org.iclass.spring_9jwt.entity.UsersEntity;
import org.iclass.spring_9jwt.repository.BoardRepository;
import org.iclass.spring_9jwt.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<BoardResponse> getAllBoards() {
        List<BoardEntity> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        return boards.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 내 글 보기
    public List<BoardResponse> getMyBoards(String username) {
        List<BoardEntity> boards = boardRepository.findByUsernameOrderByCreatedAtDesc(username);
        return boards.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public BoardResponse getBoard(Long id) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found: " + id));

        return convertToResponse(board);
    }

    public BoardResponse createBoard(BoardRequest request, String username) {
        UsersEntity user = userRepository.findByUsername(username);
        if (user == null)
            new EntityNotFoundException("User not found: " + username);

        BoardEntity board = BoardEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .username(username)
                .build();

        BoardEntity savedBoard = boardRepository.save(board);
        return convertToResponse(savedBoard);
    }

    public BoardResponse updateBoard(Long id, BoardRequest request, String username) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found: " + id));

        // 작성자 확인
        if (!board.getUsername().equals(username)) {
            throw new AccessDeniedException("You can only update your own posts");
        }

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());

        BoardEntity updatedBoard = boardRepository.save(board);
        return convertToResponse(updatedBoard);
    }

    public void deleteBoard(Long id, String userEmail) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found: " + id));

        // 작성자 확인
        if (!board.getUsername().equals(userEmail)) {
            throw new AccessDeniedException("You can only delete your own posts");
        }

        boardRepository.delete(board);
    }

    private BoardResponse convertToResponse(BoardEntity board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getUsername())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}

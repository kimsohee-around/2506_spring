package org.iclass.board.controller;

import java.time.LocalDate;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.iclass.board.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;<<<<<<<HEAD
import org.springframework.web.bind.annotation.SessionAttribute;=======
import org.springframework.web.bind.annotation.RequestParam;>>>>>>>4479d 49(게시판-글목록페이지)
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class CommunityController {
	private CommunityService service;

	@GetMapping("/community/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {

		PageResponseDTO pageList = service.getPageList(page);
		model.addAttribute("pageList", pageList);
		model.addAttribute("page", page); // 검색기능 구현하면 dto 로 작성
		log.info("오늘 날짜 : {}", LocalDate.now());
		model.addAttribute("today", LocalDate.now());
		return "community/list"; // community 폴더안에 list.html
	}

	// 글 읽기
	@GetMapping("/community/read")
	public String read(int idx, int page, Model model) {
		// ㄴ list.html 화면에서 글제목 링크에 page 파라미터 받아오기
		log.info("idx : {} ,  page : {}", idx, page);
		// sql : idx 값으로 하나의 행 조회, mapper, service
		model.addAttribute("dto", service.read(idx, true));
		model.addAttribute("page", page);
		// ㄴ read.html 화면 목록/수정/삭제 버튼 링크에 page 파라미터 값으로 사용
		return "community/read";
	}

	// 글 쓰기
	@GetMapping("/community/write")
	public String write(HttpSession session) throws IllegalAccessException {
		String username = (String) session.getAttribute("username");
		log.info("username : {}", username);
		if (username == null) {
			// 로그인 안했으면 예외 발생
			throw new IllegalAccessException("잘못된 접근입니다.");
		}
		return "community/write";
	}

	@PostMapping("/community/write")
	public String write(CommunityDTO dto) {
		log.info("form 입력값 : {}", dto);
		service.write(dto);
		return "redirect:list";
	}

	// 글 수정
	@GetMapping("/community/modify")
	public String modify(int idx,
			@SessionAttribute(required = false) String username,
			Model model) throws IllegalAccessException {
		CommunityDTO dto = service.read(idx, false); // 글 수정은 조회수 카운트 안함
		// 글번호 idx 의 작성자와 세션의 username 과 동일한지 비교
		log.info("username : {}", username);
		if (!dto.getWriter().equals(username)) {
			throw new IllegalAccessException("잘못된 접근입니다.");
		}
		model.addAttribute("dto", dto);
		return "community/modify"; // write.html 동일한 css 활용
	}

	// 수정할 내용 저장
	@PostMapping("/community/modify")
	public String modify(CommunityDTO dto,
			@SessionAttribute String username,
			RedirectAttributes reAttr) throws IllegalAccessException {
		// 글번호 idx 의 작성자와 세션의 username 과 동일한지 비교
		log.info("username : {}", username);
		if (!service.read(dto.getIdx(), false).getWriter().equals(username)) {
			throw new IllegalAccessException("잘못된 접근입니다.");
		}

		log.info("modify dto : {}", dto);
		service.save(dto);
		reAttr.addAttribute("idx", dto.getIdx()); // redirect 할때 애트리뷰트(파라미터) 저장
		// 수정한 글 저장 상태 확인
		// return "redirect:read?idx="+ dto.getIdx();
		return "redirect:read";
	}

	// 글 삭제
	@GetMapping("/community/remove")
	public String remove(int idx,
			@SessionAttribute(required = false) String username,
			RedirectAttributes reAttr) throws IllegalAccessException {
		if (!service.read(idx, false).getWriter().equals(username)) {
			throw new IllegalAccessException("잘못된 접근입니다.");
		}
		service.remove(idx);
		return "redirect:list";
	}

}

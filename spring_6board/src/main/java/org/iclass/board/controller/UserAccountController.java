package org.iclass.board.controller;

import org.iclass.board.dto.UserAccount;
import org.iclass.board.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@SessionAttributes({"referer"})   //로그인 후 돌아갈 페이지 세션 저장
public class UserAccountController {
	private final UserAccountService service;
		
	@GetMapping("login")
	public String login(HttpServletRequest request , Model model) {   // 로그인 화면 GET 요청
		String referer = request.getHeader("Referer");
		if(referer !=null) {  //Referer 이름의 헤더 값이 있으면 애트리뷰트 저장
			model.addAttribute("referer", referer);
			log.info("돌아갈 URL : {} ",referer);
		}
		return "login";    //login.html : form 과 input,button
	}
	
	@PostMapping("/login") 
	public String action(String userid, String password , 
			@SessionAttribute(name="referer", required = false) String referer,
			HttpSession	session	,RedirectAttributes reAttr) {
		
		UserAccount account = service.login(userid,password);
		log.info("login 계정 정보 : {} ", account);
		
		if(account != null) {
			// ★★★★★★★ 로그인기능-세션활용
			session.setAttribute("username", account.getUserid());   
			session.setAttribute("sessionId", session.getId());      // 비동기 요청에 사용
			if (referer.contains("/login")) referer="/";
			return "redirect:"+referer;     //저장되 세션 애트리뷰트 referer 로 리다이렉트
		}else {  //로그인 실패
			reAttr.addFlashAttribute("fail", "y");
					// ㄴ login.html (화면) 으로 직접 전달하는 값
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,RedirectAttributes reAttr) {
		session.invalidate();
		reAttr.addFlashAttribute("message", "로그 아웃 했습니다.");
		return "redirect:/";    // context path 라고 부르고 화면은 index.html
	}
	

}

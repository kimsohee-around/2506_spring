package org.iclass.spring_5webmvc.controller;

import java.net.http.HttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes(names = { "username" }) // 세션 스콥의 Model 애트리뷰트 이름
public class TestController_6Session {

  @GetMapping("sessionA")
  public String setSessionA(HttpServletRequest request, Model model) {

    HttpSession session = request.getSession(); // 방법1) 현재 요청 객체로부터 세션 객체 생성하기(서블릿 방식)
    session.setAttribute("userid", "hongGD"); // 서버의 세션 저장소에 데이터 저장.

    return "redirect:getSessionAll";
  }

  @GetMapping("sessionB")
  public String setSessionB(Model model) {
    model.addAttribute("username", "홍지디");
    // Model 객체에 데이터 저장
    // -> 지정된 View 로 전달(기존 사용 request scope)
    // -> 방법 2) Model 객체를 session scope 으로 저장하려면(스프링 방식). @SessionAttributes 어노테이션으로
    // 설정.
    return "redirect:getSessionAll";
  }

  @GetMapping("getSessionAll")
  public String getSessionAll() {
    return "session"; // session.html
  }

  @GetMapping("getSessionAnnotation")
  public String getSession( // required = false 는 애트리뷰트 값이 없을 수도 있을 때. (Model 에 값이 없으면 오류 )
      @SessionAttribute(required = false) String userid,
      @SessionAttribute(required = false) String username) {
    log.info("✅ 어노테이션으로 2개의 값을 모두 가져오는지 확인하기!!");
    // 결론 : 방법 1) 2) 모두 가져옵니다. 세션 애트리뷰트 이름은 기본값이 변수명입니다.
    log.info("session userid : {}", userid);
    log.info("session username : {}", username);
    return "session";
  }

  @GetMapping("logout") // 방법 1: JSESSIONID 값 초기화
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/getSessionAll";
  }

  @GetMapping("logoutA") // 방법 1 : JSESSIONID 값은 유지
  public String logoutA(HttpSession session) { // HttpSession session 을 선언하여 사용도 가능
    // HttpSession session = request.getSession();
    // 기존 애트리뷰트 삭제 : JSESSIONID 값은 유지
    session.removeAttribute("userid");
    session.removeAttribute("username"); // 어노테이션으로 설정: 이 명령어로는 삭제 안됨.
    log.info("logoutA ***");
    return "redirect:/getSessionAll";
  }

  @GetMapping("logoutB") // 방법 2
  public String logout(SessionStatus sessionStatus) {
    // model 애트리뷰트 중에서 session 스콥에 저장된 것들을 모두 삭제
    log.info("logoutB ***");

    sessionStatus.setComplete(); // JSESSIONID 값은 유지

    return "redirect:/getSessionAll";
  }

}
/*
 * HTTP 프로토콜 (무상태 stateless)
 * 클라이언트 요청으로 서버가 응답을 보낸 이후 연결 상태를 유지하지 않아요!!
 * 
 * 필요에 따라 클라이언트 브라우저의 쿠키를 활용하여 서버가 보낸 데이터를 저장.
 * 다음 요청에 서버가 그 쿠키 데이터(key,value)를 활용할 수 있습니다.
 * 
 * 예시 : JSESSIONID (톰캣 서버가 클라이언트를 식별하기 위해 부여하는 값)
 * 
 * 클라이언트가 www.test.org 로 첫 요청을 보냅니다.
 * →톰캣서버는 JSESSIONID 쿠키를 클라이언트에 보냅니다.
 * →클라이언트 브라우저에 JSESSIONID 쿠키값이 저장됩니다.
 * →클라이언트는 다음 요청부터 JSESSIONID 쿠키를 함께 서버에게 보냅니다.(브라우저 종료시 삭제)
 * ✅임의로 삭제 -> 로그아웃
 * 
 * 
 * 웹애플리케이션에서 세션이란????
 * -> 로그인 이후 부터 클라이언트가 요청/응답 활동을 하는 시간. 로그아웃 또는 브라우저 종료까지 유지.
 */
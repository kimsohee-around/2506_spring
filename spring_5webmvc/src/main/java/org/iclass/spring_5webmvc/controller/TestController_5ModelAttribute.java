package org.iclass.spring_5webmvc.controller;

import org.iclass.spring_3mybatis.mapper.ProductMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class TestController_5ModelAttribute {
  // 자동 주입 필요 -> 생성자
  private ProductMapper productMapper;

  @GetMapping("/product/search")
  public String search(@ModelAttribute String keyword,
      @RequestParam(defaultValue = "1") @ModelAttribute(name = "page") int page) {
    log.info("파라미터 : {} , {}", keyword, page);

    return "product/search";
  }

  // 연습
  @GetMapping("/product/list")
  public String prod_list(Model model) {
    // 테이블에서 모든 행을 조회하여 애트리뷰트 저장&view 로 전달
    model.addAttribute("list", productMapper.selectAll());
    return "product/list";
  }

}

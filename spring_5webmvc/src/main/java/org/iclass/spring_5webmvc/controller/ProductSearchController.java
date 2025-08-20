package org.iclass.spring_5webmvc.controller;

import org.iclass.spring_3mybatis.dto.ProductSearchDto;
import org.iclass.spring_3mybatis.mapper.ProductMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class ProductSearchController {
  private ProductMapper productMapper;

  @GetMapping("/product/searchAll")
  public String searchAll(Model model) { // 전체 목록
    model.addAttribute("list", productMapper.selectAll());
    return "product/searchAll";
  }

  // 검색을 위한 Post 요청 처리
  @PostMapping("/product/searchAll")
  public String search(@ModelAttribute(name = "searchDto") ProductSearchDto dto, Model model) {
    model.addAttribute("list", productMapper.searchAll(dto));

    return "product/searchAll";
  }

  // @ModelAttribute 애트리뷰트를 사용하지 않는다면.....아래와 같이 작성해야 합니다.
  // 추가로 GetMapping, PostMapping 각각 테스트하여 비교 해보세요.
  @GetMapping("/product/searchAllTest")
  public String searchTest(ProductSearchDto dto, Model model) {
    // ㄴ '인자로 선언'한 ProductSearchDto dto 는 파라미터들을 저장
    model.addAttribute("list", productMapper.searchAll(dto));
    // ㄴ '애트리뷰트'는 searchAll.html 에 전달할 데이터.  
    model.addAttribute("searchDto", dto);
    // ㄴ 파라미터 dto 또한 searchAll.html 에 전달하여 화면에 표시
    return "product/searchAll";
  }
}

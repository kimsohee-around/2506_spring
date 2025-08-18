package org.iclass.spring_5webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController_3FileUpload {

  @PostMapping("upload")
  public String upload(String title, MultipartFile uploadFile) {
    // MultipartFile uploadFile 선언해서 업로드한 파일을 전송 받습니다.
    log.info("파일명 : {}", uploadFile.getOriginalFilename());
    log.info("파일크기 : {}", uploadFile.getSize());
    return "redirect:/";
    // 루트 컨텍스트 (index.html) 로 리다이렉트
  }
}

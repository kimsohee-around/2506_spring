package org.iclass.spring_5webmvc.controller;

import java.io.File;
import java.io.IOException;

import org.iclass.spring_5webmvc.Spring5webmvcApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController_3FileUpload {

  private final Spring5webmvcApplication spring5webmvcApplication;

  TestController_3FileUpload(Spring5webmvcApplication spring5webmvcApplication) {
    this.spring5webmvcApplication = spring5webmvcApplication;
  }

  @PostMapping("upload")
  public String upload(String title, MultipartFile uploadFile) {
    // MultipartFile uploadFile 선언해서 업로드한 파일을 전송 받습니다.
    log.info("파일명 : {}", uploadFile.getOriginalFilename());
    log.info("파일크기 : {}", uploadFile.getSize());

    // 아래 코드는 Service 에서 하는 것이 더 맞습니다.
    try {
      if (uploadFile.getSize() != 0) {
        // 서버에 저장될 파일 객체를 생성.(경로+파일명)
        File path = new File("C:\\Class250615\\upload\\" + uploadFile.getOriginalFilename());
        // uploadFile 객체를 path 에 대입합니다.
        // (요청 파일 입력스트림을 path 지정 출력스트림으로 보냄)
        uploadFile.transferTo(path);
      }
    } catch (IOException e) {
      log.info("upload 예외 :  {}", e.getMessage());
    }
    return "redirect:/";
    // 루트 컨텍스트 (index.html) 로 리다이렉트
  }
  // 참고(예고) : 서버의 업로드 폴더 C:\\Class250615\\upload\\ 는 
  //        웹에서 접근하려면 URL로 사용할 경로이름이
  //        설정되어야 합니다.
}

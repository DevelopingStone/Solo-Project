package com.example.weddinginvitation.map.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/map")
public class MapController {

    private final ResourceLoader resourceLoader;

    @GetMapping("/location")
    public ResponseEntity<String> loadFile() {
        try {
            Resource resource = resourceLoader.getResource("classpath:kakao_map.html");

            // 파일을 문자열로 읽어오기
            String fileContent = new String(resource.getInputStream().readAllBytes());
            // 파일 내용 반환
            return ResponseEntity.ok(fileContent);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("해당 경로에 kakao_map.html 파일이 없습니다.");
        }
    }
}

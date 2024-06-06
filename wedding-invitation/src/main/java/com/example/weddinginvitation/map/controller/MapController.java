package com.example.weddinginvitation.map.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/map")
public class MapController {

    private final ResourceLoader resourceLoader;

    @GetMapping("/location")
    public ResponseEntity<String> loadFile() {
        try {
            Resource resource = resourceLoader.getResource("classpath:map/kakao_map.html");

            log.info("파일을 문자열로 읽기");
            String fileContent = new String(resource.getInputStream().readAllBytes());
            return ResponseEntity.ok(fileContent);

        } catch (Exception e) {
            log.error("해당 경로에 파일이 없습니다. : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("해당 경로에 kakao_map.html 파일이 없습니다.");

        }
    }
}

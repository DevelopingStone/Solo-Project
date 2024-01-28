package stone.weatherdiary.controller;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stone.weatherdiary.service.DiaryService;

@RequestMapping("/diary")
@RestController
@AllArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;


    @PostMapping("/create")
    public ResponseEntity<String> diaryCreate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                              @RequestBody String text) {
        diaryService.createDiary(date, text);
        return null;
    }


}

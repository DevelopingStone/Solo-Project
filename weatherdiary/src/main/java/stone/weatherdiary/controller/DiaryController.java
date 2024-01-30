package stone.weatherdiary.controller;


import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stone.weatherdiary.domain.Diary;
import stone.weatherdiary.service.DiaryService;

@RequestMapping("/diary")
@RestController
@AllArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;


    @PostMapping("/create")
    public ResponseEntity<Diary> diaryCreate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                             @RequestBody String text) {
        Diary diary = diaryService.createDiary(date, text);
        return ResponseEntity.ok(diary);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Diary>> readDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        List<Diary> diary = diaryService.readDiary(date);
        return ResponseEntity.ok(diary);
    }

    @GetMapping("/readList")
    public ResponseEntity<List<Diary>> readListDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
                                                     @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        List<Diary> diary = diaryService.readListDiary(startDate, endDate);
        return ResponseEntity.ok(diary);
    }

    @PutMapping("/update")
    public ResponseEntity<Diary> updateDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate updateDate,
                                             @RequestBody String text) {
        Diary diary;
        try {
            diary = diaryService.updateDiary(updateDate, text);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("없는 데이터 입니다.");
        }
        return ResponseEntity.ok(diary);
    }


}

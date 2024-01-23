package stone.weatherdiary.controller;


import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stone.weatherdiary.service.DiaryService;

@RestController
@RequestMapping("/diary")
//@AllArgsConstructor
//@NoArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/create?date")
    public ResponseEntity<String> diaryCreate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                              @RequestBody String text) {

        return null;
    }


}

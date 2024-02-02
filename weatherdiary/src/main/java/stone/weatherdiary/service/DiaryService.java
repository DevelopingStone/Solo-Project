package stone.weatherdiary.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stone.weatherdiary.WeatherdiaryApplication;
import stone.weatherdiary.domain.DateWeather;
import stone.weatherdiary.domain.Diary;
import stone.weatherdiary.repository.DateWeatherRepository;
import stone.weatherdiary.repository.DiaryRepository;

@Service
@Transactional(readOnly = false)
public class DiaryService {

    private final DiaryRepository diaryRepository;

    private final DateWeatherRepository dateWeatherRepository;

    @Value("${weatherperson.key}")
    private String apikey;

    public DiaryService(DiaryRepository diaryRepository, DateWeatherRepository dateWeatherRepository) {
        this.diaryRepository = diaryRepository;
        this.dateWeatherRepository = dateWeatherRepository;
    }

    public Diary createDiary(LocalDate date, String text) {
        logger.info("started to create diary");

//        open weather map 에서 날씨 데이터 가져오기
        String weatherData = getWeatherString();

//        날씨 데이터 파싱하기
        Map<String, Object> parseWeather = parseWeather(weatherData);

//        날씨 데이터 가져오기 ( DB )
//        DateWeather dateWeather = getDateWeather(date);


        logger.info("end to create diary");

//        데이터 파싱 + DB 저장
        return setDbData(date, text, parseWeather);
    }

    private DateWeather getDateWeather(LocalDate date) {
        List<DateWeather> dateWeatherListFromDB = dateWeatherRepository.findAllByDate(date);
        if (dateWeatherListFromDB.isEmpty()) {
//            새로 api에서 날씨 정보를 가져와야한다.
//            정책상 현재 날씨를 가져오도록 하거나 / 날씨 없이 일기만 쓰기
            return getWeatherFromApi();
        } else {
            return dateWeatherListFromDB.get(0);
        }
    }

    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apikey;
        try {
//            String 형식을 URL 형식으로 변경
            URL url = new URL(apiUrl);

//            API URL을 Http 형식으로 변경
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//            방식은 GET 방식으로
            connection.setRequestMethod("GET");

//            응답에 대한 결과값 확인 200,400,404
            int responseCode = connection.getResponseCode();

//            버퍼드리더를 통해 한줄씩 불러옴, 통으로 데이터를 불러왔을때보다 속도는 느리지만 성능 향상
            BufferedReader br;

//          코드값이 200인경우 겟인풋스티림 입력
            if (responseCode == 200) {
//
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;

//            스트링 빌더값 추가, String + 값보다 빌더값이 효율적
            StringBuilder response = new StringBuilder();

//            br 값에서 한줄씩 읽어옴, 널이 아닌경우 스트링빌더에 추가
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();

        } catch (Exception e) {
            return "failed to get response";
        }

    }

    private Map<String, Object> parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> resultMap = new HashMap<>();

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));
        return resultMap;
    }

    private Diary setDbData(LocalDate date, String text, Map<String, Object> parseWeather) {
        Diary nowDiary = Diary.builder().weather(parseWeather.get("main").toString())
                .icon(parseWeather.get("icon").toString()).temp((Double) parseWeather.get("temp")).text(text).date(date)
                .build();
        diaryRepository.save(nowDiary);
        return nowDiary;
    }

    public List<Diary> readDiary(LocalDate date) {
        logger.debug("started to read diary");
        return diaryRepository.findAllByDate(date);
    }

    public List<Diary> readListDiary(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    public Diary updateDiary(LocalDate date, String text) {
        Diary diary = diaryRepository.getFirstByDate(date);
        if (diary == null) {
            try {
                throw new IllegalAccessException("없는 날씨일기 데이터 입니다. 유효한 날짜를 다시 입력해주세요.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        diary.setText(text);
        return diaryRepository.save(diary);
    }

    public Optional<Diary> deleteDiary(Long id) {
        Optional<Diary> byId = diaryRepository.findById(id);
        if (byId.isPresent()) {
            diaryRepository.deleteById(id);
        }

        return byId;
    }

    public Optional<Diary> deleteAllDiary(LocalDate date) {
        return diaryRepository.deleteAllByDate(date);
    }

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void saveWeatherDate() {
        dateWeatherRepository.save(getWeatherFromApi());
    }

    private DateWeather getWeatherFromApi() {
        //        open weather map 에서 날씨 데이터 가져오기
        String weatherData = getWeatherString();

        //        날씨 데이터 파싱하기
        Map<String, Object> parseWeather = parseWeather(weatherData);

        return DateWeather.builder().
                date(LocalDate.now()).
                weather(parseWeather.get("main").toString()).
                icon(parseWeather.get("icon").toString()).
                temperature((Double) parseWeather.get("temp")).
                build();
    }

    private static final Logger logger = LoggerFactory.getLogger(WeatherdiaryApplication.class);
}


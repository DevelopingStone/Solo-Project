package stone.weatherdiary.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stone.weatherdiary.repository.DiaryRepository;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Value("${weatherperson.key}")
    private String apikey;

    public void createDiary(LocalDate date, String text) {
        String weatherData = getWeatherString();

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

    private void parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonParser.parse(jsonString);
        } catch (Exception e) {

        }

    }

    private void setDbData() {
//        diaryRepository.save();
    }

}


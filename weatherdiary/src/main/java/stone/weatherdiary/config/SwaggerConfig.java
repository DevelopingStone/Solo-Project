package stone.weatherdiary.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("날씨일기 프로젝트")
                        .description("하루 기분을 날씨로 표현하여 일기를 작성")
                        .version("1.0.0"));


    }
}


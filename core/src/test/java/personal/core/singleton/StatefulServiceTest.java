package personal.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext(TestConfig.class);
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac1.getBean(StatefulService.class);
        StatefulService statefulService2 = ac2.getBean(StatefulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice();

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }


}
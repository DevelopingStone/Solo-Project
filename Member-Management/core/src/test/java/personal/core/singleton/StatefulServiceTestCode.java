package personal.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTestCode {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext(TestConfig.class);
//        AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac1.getBean(StatefulService.class);
        StatefulService statefulService2 = ac1.getBean(StatefulService.class);

        int userA = statefulService1.order("userA", 10000);
        int userB = statefulService2.order("userB", 20000);

        Assertions.assertThat(userA).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
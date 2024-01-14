package personal.core.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import personal.core.AppConfig;

class MemberServiceImplTestCode {

    @Test
    void memberServiceTest() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //when
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        //then

        Assertions.assertThat(memberService.getMemberRepository()).isEqualTo(orderService.getMemberRepository());

    }

}
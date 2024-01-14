package personal.core;

import org.springframework.stereotype.Component;
import personal.core.discount.RateDiscountPolicy;
import personal.core.repository.MemoryMemberRepository;
import personal.core.service.MemberService;
import personal.core.service.MemberServiceImpl;
import personal.core.service.OrderService;
import personal.core.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Component
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public RateDiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}

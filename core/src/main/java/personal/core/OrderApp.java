package personal.core;


import personal.core.member.Grade;
import personal.core.member.Member;
import personal.core.order.Order;
import personal.core.service.MemberService;
import personal.core.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

//        MemberRepository memberRepository = new MemoryMemberRepository();
//        DiscountPolicy discountPolicy = new RateDiscountPolicy();
//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "hint", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "LOL", 13500);
        System.out.println("order = " + order);
        System.out.println(order.calculatePrice());


    }
}

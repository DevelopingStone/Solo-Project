package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import hello.core.service.OrderService;
import hello.core.service.OrderServiceImpl;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {


//        MemberRepository memberRepository = new MemoryMemberRepository();
//        DiscountPolicy discountPolicy = new RateDiscountPolicy();
//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();



        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 15000);
        System.out.println(order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());



    }
}

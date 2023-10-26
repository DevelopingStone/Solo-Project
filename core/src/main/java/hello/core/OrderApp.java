package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        MemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberServiceImpl(memberRepository);

        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10800);
        System.out.println(order);


    }
}

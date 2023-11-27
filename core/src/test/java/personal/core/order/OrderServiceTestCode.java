package personal.core.order;

import personal.core.AppConfig;
import personal.core.member.Grade;
import personal.core.member.Member;
import personal.core.service.MemberService;
import personal.core.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTestCode {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
        orderService = appconfig.orderService();
    }


    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "Hint", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 12000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1200);

    }


}

package personal.core.discount;

import static org.assertj.core.api.Assertions.assertThat;

import personal.core.member.Grade;
import personal.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {


    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();


    @Test
    @DisplayName("VIP 10% 할인 적용 : O")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount1 = discountPolicy.discount(member, 15000);
        int discount2 = discountPolicy.discount(member, 13500);
        //then
        assertThat(discount1).isEqualTo(1500);

        //fail
        assertThat(discount2).isEqualTo(1350);
    }

    @Test
    @DisplayName("VIP 아닌경우, 10% 할인 적용 : X")
    void vip_x() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.BASIC);
        //when
        int discount1 = discountPolicy.discount(member, 15000);
        int discount2 = discountPolicy.discount(member, 13500);
        //then
        assertThat(discount1).isEqualTo(0);

        //fail
        assertThat(discount2).isEqualTo(0);
    }


}
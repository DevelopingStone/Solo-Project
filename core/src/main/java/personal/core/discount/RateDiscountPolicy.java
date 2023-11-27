package personal.core.discount;

import org.springframework.stereotype.Component;
import personal.core.member.Grade;
import personal.core.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private static final double DISCOUNT_RATE = 0.1;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return (int) (price * DISCOUNT_RATE);
        }
        return 0;
    }
}

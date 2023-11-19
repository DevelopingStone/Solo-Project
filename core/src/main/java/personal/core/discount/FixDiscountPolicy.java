package personal.core.discount;

import personal.core.member.Grade;
import personal.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT = 1000;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT;
        }

        return 0;

    }
}

package hello.core.member;

import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    @Test
    public void memberServiceTest() {
        MemberService memberService = new MemberServiceImpl();
        Member user = new Member(1L, "hint", Grade.VIP);
        memberService.join(user);
        Member userTest = memberService.findMember(1L);

        Assertions.assertEquals(user.getName(),userTest.getName());
    }
}

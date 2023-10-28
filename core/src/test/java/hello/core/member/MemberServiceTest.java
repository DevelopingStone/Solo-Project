package hello.core.member;

import hello.core.AppConfig;
import hello.core.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
//        MemberRepository memberRepository = new MemoryMemberRepository();
//        MemberService memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    public void memberServiceTest() {
        Member user = new Member(1L, "hint", Grade.VIP);
        memberService.join(user);
        Member userTest = memberService.findMember(1L);
        Assertions.assertEquals(user.getName(), userTest.getName());
    }
}

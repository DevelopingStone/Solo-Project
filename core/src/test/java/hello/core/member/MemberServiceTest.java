package hello.core.member;

import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    @Test
    public void memberServiceTest() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberServiceImpl(memberRepository);
        Member user = new Member(1L, "hint", Grade.VIP);
        memberService.join(user);
        Member userTest = memberService.findMember(1L);

        Assertions.assertEquals(user.getName(), userTest.getName());
    }
}

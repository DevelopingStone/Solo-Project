package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        Member user = new Member(1L, "hint", Grade.VIP);
        memberService.join(user);

        Member userTest = memberService.findMember(1L);
        System.out.println("user = " + user.getName());
        System.out.println("userTest = " + userTest.getName());

    }
}

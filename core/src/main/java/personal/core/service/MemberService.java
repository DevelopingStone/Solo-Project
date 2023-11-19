package personal.core.service;


import personal.core.member.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}

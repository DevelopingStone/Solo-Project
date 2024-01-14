package personal.core.repository;

import personal.core.member.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}

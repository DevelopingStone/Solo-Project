package com.example.weddinginvitation.member.repository;

import com.example.weddinginvitation.member.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByTextAuthenticationNumber(String authenticationNumber);
    Optional<MemberEntity> findByEmail(String email);

}

package com.example.weddinginvitation.member.repository;

import com.example.weddinginvitation.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

}

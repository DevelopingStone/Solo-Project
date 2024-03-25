package com.store.reservation.member.repository;

import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.member.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByPhoneNumber(String phoneNumber);

}

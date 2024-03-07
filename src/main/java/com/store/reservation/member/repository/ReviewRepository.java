package com.store.reservation.member.repository;

import com.store.reservation.admin.entity.PartnerEntity;
import com.store.reservation.member.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {



}

package com.store.reservation.admin.repository;

import com.store.reservation.admin.entity.PartnerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {

    Optional<PartnerEntity> findByPhoneNumber(String phoneNumber);
}

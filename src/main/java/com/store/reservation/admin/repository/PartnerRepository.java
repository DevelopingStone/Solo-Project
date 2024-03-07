package com.store.reservation.admin.repository;

import com.store.reservation.admin.entity.PartnerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {

    Optional<PartnerEntity> findByPhoneNumber(String phoneNumber);
}

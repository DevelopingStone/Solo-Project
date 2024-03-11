package com.store.reservation.reserve.repository;

import com.store.reservation.reserve.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

}

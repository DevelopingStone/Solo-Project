package com.store.reservation.admin.repository;

import com.store.reservation.admin.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}

package com.store.reservation.admin.repository;

import com.store.reservation.admin.entity.StoreEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    StoreEntity findByStoreName(String storeName);
}

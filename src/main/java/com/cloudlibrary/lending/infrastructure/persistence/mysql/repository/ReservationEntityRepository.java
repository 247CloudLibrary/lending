package com.cloudlibrary.lending.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByUid(Long uid);
}

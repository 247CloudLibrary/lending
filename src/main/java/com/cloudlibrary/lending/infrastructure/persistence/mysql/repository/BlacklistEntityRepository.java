package com.cloudlibrary.lending.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlacklistEntityRepository extends JpaRepository<BlacklistEntity, Long> {

    List<BlacklistEntity> findByUidAndLibraryId(Long uid, Long libraryId);
}

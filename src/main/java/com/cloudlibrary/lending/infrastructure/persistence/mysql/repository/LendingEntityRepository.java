package com.cloudlibrary.lending.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LendingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LendingEntityRepository extends CrudRepository<LendingEntity, Long> {
    List<LendingEntity> findByUidAndLibraryId(Long uid, Long libraryId);
}

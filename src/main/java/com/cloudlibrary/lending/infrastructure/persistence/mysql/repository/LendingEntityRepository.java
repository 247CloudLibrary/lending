package com.cloudlibrary.lending.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LendingEntity;
import org.springframework.data.repository.CrudRepository;

public interface LendingEntityRepository extends CrudRepository<LendingEntity, Long> {
}

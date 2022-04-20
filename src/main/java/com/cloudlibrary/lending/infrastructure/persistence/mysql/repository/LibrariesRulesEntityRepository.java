package com.cloudlibrary.lending.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LibrariesRulesEntity;
import org.springframework.data.repository.CrudRepository;

public interface LibrariesRulesEntityRepository extends CrudRepository<LibrariesRulesEntity, Long> {
}

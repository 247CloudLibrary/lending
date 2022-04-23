package com.cloudlibrary.lending.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LibrariesRulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrariesRulesEntityRepository extends JpaRepository<LibrariesRulesEntity, Long> {
}

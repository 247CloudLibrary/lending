package com.cloudlibrary.lending.infrastructure.mapper;

import com.cloudlibrary.lending.application.domain.Lending;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LendingMapper {
    List<Lending> findAllByOrderByLendingDateTimeDesc();

    List<Lending> findLendingByUid(Long uid);
}

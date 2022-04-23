package com.cloudlibrary.lending.infrastructure.mapper;

import com.cloudlibrary.lending.application.domain.Blacklist;
import com.cloudlibrary.lending.application.domain.Lending;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LendingMapper {
    List<Lending> findAllByOrderByLendingDateTimeDesc();

    List<Lending> findLendingByUid(Long uid);

    void deleteBlacklist(Long uid);
}

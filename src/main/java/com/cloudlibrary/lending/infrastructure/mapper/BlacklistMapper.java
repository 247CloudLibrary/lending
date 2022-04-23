package com.cloudlibrary.lending.infrastructure.mapper;

import com.cloudlibrary.lending.application.domain.Blacklist;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface BlacklistMapper {
    List<Blacklist> findAllBlacklist();
}

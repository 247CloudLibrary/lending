package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Blacklist;
import com.cloudlibrary.lending.infrastructure.mapper.BlacklistMapper;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.BlacklistEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.BlacklistEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlacklistService implements BlacklistOperationUseCase, BlacklistReadUseCase {

    private final BlacklistEntityRepository blacklistEntityRepository;
    private final BlacklistMapper blacklistMapper;

    @Autowired
    public BlacklistService(BlacklistEntityRepository blacklistEntityRepository, BlacklistMapper blacklistMapper){
        this.blacklistEntityRepository = blacklistEntityRepository;
        this.blacklistMapper = blacklistMapper;
    }


    @Override
    public FindBlacklistResult createBlacklist(BlacklistCreatedCommand command) {
        Blacklist blacklist = Blacklist.builder()
                .uid(command.getUid())
                .libraryId(command.getLibraryId())
                .libraryName(command.getLibraryName())
                .build();

        BlacklistEntity blacklistEntity = new BlacklistEntity(blacklist);
        blacklistEntityRepository.save(blacklistEntity);

        blacklist = blacklistEntity.toBlacklist();
        return FindBlacklistResult.findByBlacklist(blacklist);
    }

    @Override
    public List<FindBlacklistResult> getAllBlacklist() {
        return blacklistMapper.findAllBlacklist().stream().map(FindBlacklistResult::findByBlacklist).collect(Collectors.toList());
    }
}

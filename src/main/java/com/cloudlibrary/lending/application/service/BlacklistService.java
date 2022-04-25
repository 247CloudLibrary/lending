package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Blacklist;
import com.cloudlibrary.lending.infrastructure.mapper.LendingMapper;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.BlacklistEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.BlacklistEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlacklistService implements BlacklistOperationUseCase, BlacklistReadUseCase {

    private final BlacklistEntityRepository blacklistEntityRepository;
    private final LendingMapper lendingMapper;

    @Autowired
    public BlacklistService(BlacklistEntityRepository blacklistEntityRepository, LendingMapper lendingMapper){
        this.blacklistEntityRepository = blacklistEntityRepository;
        this.lendingMapper = lendingMapper;
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
        var results = blacklistEntityRepository.findAll();
        List<FindBlacklistResult> findBlacklistResults = new ArrayList<>();

        for(BlacklistEntity blacklistEntity : results){
            findBlacklistResults.add(FindBlacklistResult.findByBlacklist(blacklistEntity.toBlacklist()));
        }

        return findBlacklistResults;
    }

    @Override
    public void deleteBlacklist(BlacklistDeleteCommand command) {
        lendingMapper.deleteBlacklist(command.getUid());
    }

}

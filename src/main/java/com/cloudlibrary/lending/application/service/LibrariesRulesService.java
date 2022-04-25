package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Blacklist;
import com.cloudlibrary.lending.application.domain.LibrariesRules;
import com.cloudlibrary.lending.infrastructure.mapper.LendingMapper;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LibrariesRulesEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.BlacklistEntityRepository;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.LendingEntityRepository;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.LibrariesRulesEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LibrariesRulesService implements LibrariesRulesReadUseCase, LibrariesRulesOperationUseCase {

    private final LendingEntityRepository lendingEntityRepository;
    private final LibrariesRulesEntityRepository librariesRulesEntityRepository;
    private final BlacklistEntityRepository blacklistEntityRepository;

    public LibrariesRulesService(LibrariesRulesEntityRepository librariesRulesEntityRepository
    , BlacklistEntityRepository blacklistEntityRepository
    , LendingEntityRepository lendingEntityRepository) {
        this.librariesRulesEntityRepository = librariesRulesEntityRepository;
        this.blacklistEntityRepository = blacklistEntityRepository;
        this.lendingEntityRepository = lendingEntityRepository;
    }

    @Override
    public Boolean isLendingPossible(IsPossibleFindQuery query) {
        var list = blacklistEntityRepository.findByUidAndLibraryId(query.getUid(), query.getLibraryId());
        if(!list.isEmpty()){
            return false;
        }

        var rules = librariesRulesEntityRepository.findById(query.getLibraryId());
        var lendingCount = lendingEntityRepository.findByUidAndLibraryId(query.getUid(), query.getLibraryId());

        if(rules.get().getLendingAvailableCount()<=lendingCount.size()){
            return false;
        }
        return true;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public FindLibrariesRulesResult createLibrariesRules(LibrariesRulesCreatedCommand command) {

        LibrariesRules librariesRules = LibrariesRules.builder()
                .libraryId(command.getLibraryId())
                .libraryName(command.getLibraryName())
                .lendingAvailableCount(command.getLendingAvailableCount())
                .lengdingAvailableDays(command.getLengdingAvailableDays())
                .overdueCount(command.getOverdueCount())
                .longtermOverdueDays(command.getLongtermOverdueDays())
                .lendingLimitDays(command.getLendingLimitDays())
                .build();

        LibrariesRulesEntity librariesRulesEntity = new LibrariesRulesEntity(librariesRules);
        librariesRulesEntityRepository.save(librariesRulesEntity);

        librariesRules = librariesRulesEntity.toLibrariesRules();

        return FindLibrariesRulesResult.findByLibrariesRules(librariesRules);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void deleteLibrariesRules(LibrariesRulesDeleteCommand command) {
        Optional<LibrariesRulesEntity> librariesRulesEntity = librariesRulesEntityRepository.findById(command.getLibraryId());
        librariesRulesEntityRepository.delete(librariesRulesEntity.get());
    }
}

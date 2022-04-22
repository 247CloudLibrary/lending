package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.LibrariesRules;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LibrariesRulesEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.LibrariesRulesEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LibrariesRulesService implements LibrariesRulesReadUseCase, LibrariesRulesOperationUseCase {

    private final LibrariesRulesEntityRepository librariesRulesEntityRepository;

    public LibrariesRulesService(LibrariesRulesEntityRepository librariesRulesEntityRepository) {
        this.librariesRulesEntityRepository = librariesRulesEntityRepository;
    }

    @Override
    public void getLibrariesRules(LibrariesRulesFindQuery query) {

    }

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

    @Override
    public void deleteLibrariesRules(LibrariesRulesDeleteCommand command) {
        LibrariesRulesEntity librariesRulesEntity = LibrariesRulesEntity.builder().libraryId(command.getLibraryId()).build();
        librariesRulesEntityRepository.delete(librariesRulesEntity);
    }


}

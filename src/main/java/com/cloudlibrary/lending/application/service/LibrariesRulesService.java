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

import java.util.List;

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
        //TODO : uid로 블랙리스트인지 아닌지 + uid로 대출한 횟수 조회 + 도서관id로 제한숫자 조회해서 크다 적다 보기
        var list = blacklistEntityRepository.findByUidAndLibraryId(query.getUid(), query.getLibraryId());
        System.out.println("list = 매퍼에서 select로 꺼내온거 없으면 어떻게 찍히는지" + list);
        if(!list.isEmpty()){
            System.out.println("블랙리스트있어");
            return false;
        }
        System.out.println("블랙리스트없어");

        var rules = librariesRulesEntityRepository.findById(query.getLibraryId());
        var lendingCount = lendingEntityRepository.findByUidAndLibraryId(query.getUid(), query.getLibraryId());
        System.out.println("도서관 룰 렌딩 개수 세지는지 " + rules.get().getLendingAvailableCount());
        System.out.println("lendingCount = " + lendingCount.size());
        if(rules.get().getLendingAvailableCount()<=lendingCount.size()){
            return false;
        }
        return true;
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

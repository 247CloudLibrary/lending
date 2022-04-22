package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Lending;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LendingEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.LendingEntityRepository;
import com.cloudlibrary.lending.ui.feign.FeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LendingService implements LendingOperationUseCase, LendingReadUseCase {

    private final LendingEntityRepository lendingEntityRepository;
    private final FeignClient feignClient;

    public LendingService(LendingEntityRepository lendingEntityRepository, FeignClient feignClient) {
        this.lendingEntityRepository = lendingEntityRepository;
        this.feignClient = feignClient;
    }

    @Override
    public LendingReadUseCase.FindLendingResult createAdmin(LendingCreateCommand command) {
        Lending lending = Lending.builder()
                .bookId(command.getBookId())
                .uid(command.getUid())
                .libraryId(command.getLibraryId())
                .libraryName(command.getLibraryName())
                .lendingStatus(command.getLendingStatus())
                .lendingDateTime(command.getLendingDateTime())
                .returnDateTime(command.getReturnDateTime())
                .barcode(command.getBarcode())
                .rfid(command.getRfid())
                .build();
        LendingEntity lendingEntity = new LendingEntity(lending);
        lendingEntityRepository.save(lendingEntity);
        String feign = feignClient.compositeBookOut(command.getBookId(), command.getLendingStatus());
        System.out.println("feign = " + feign);
        lending = lendingEntity.toLending();

        return FindLendingResult.findByLending(lending);
    }
}

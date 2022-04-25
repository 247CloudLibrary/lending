package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Lending;
import com.cloudlibrary.lending.infrastructure.mapper.LendingMapper;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.LendingEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.LendingEntityRepository;
import com.cloudlibrary.lending.ui.feign.FeignClient;
import com.cloudlibrary.lending.ui.requestBody.FeignLendingStatusUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LendingService implements LendingOperationUseCase, LendingReadUseCase {

    private final LendingEntityRepository lendingEntityRepository;
    private final FeignClient feignClient;
    private final LendingMapper lendingMapper;

    public LendingService(LendingEntityRepository lendingEntityRepository, FeignClient feignClient, LendingMapper lendingMapper) {
        this.lendingEntityRepository = lendingEntityRepository;
        this.feignClient = feignClient;
        this.lendingMapper = lendingMapper;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public FindLendingResult createLending(LendingCreateCommand command) {
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

        FeignLendingStatusUpdateRequest lendingStatusUpdateRequest = FeignLendingStatusUpdateRequest.builder().lendingStatus(command.getLendingStatus()).build();
        feignClient.updateLendingStatus(command.getBookId(), lendingStatusUpdateRequest);
        lending = lendingEntity.toLending();

        return FindLendingResult.findByLending(lending);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public FindLendingResult updateLending(LendingUpdateCommand command) {
        Optional<LendingEntity> lendingEntity = lendingEntityRepository.findById(command.getLendingId());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.map(command, lendingEntity.get());

        lendingEntityRepository.save(lendingEntity.get());
        Lending lending = lendingEntity.get().toLending();

        FeignLendingStatusUpdateRequest lendingStatusUpdateRequest = FeignLendingStatusUpdateRequest.builder().lendingStatus(command.getLendingStatus()).build();
        String feignMesssage = feignClient.updateLendingStatus(lendingEntity.get().getBookId(), lendingStatusUpdateRequest);
        System.out.println("feignMesssage = " + feignMesssage);
        return FindLendingResult.findByLending(lending);
    }

    @Override
    public List<FindLendingResult> getAllLendingOrderByTimeDesc() {
        return lendingMapper.findAllByOrderByLendingDateTimeDesc().stream().map(FindLendingResult::findByLending).collect(Collectors.toList());
    }

    @Override
    public List<FindLendingResult> getLendingByUid(LendingFindQueryByUid query) {
        return lendingMapper.findLendingByUid(query.getUid()).stream().map(FindLendingResult::findByLending).collect(Collectors.toList());
    }
}

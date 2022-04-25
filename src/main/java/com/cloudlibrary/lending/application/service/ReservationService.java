package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Reservation;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.entity.ReservationEntity;
import com.cloudlibrary.lending.infrastructure.persistence.mysql.repository.ReservationEntityRepository;
import com.cloudlibrary.lending.ui.feign.FeignClient;
import com.cloudlibrary.lending.ui.requestBody.FeignReservationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReservationService implements ReservationReadUseCase, ReservationOperationUseCase{
    private final ReservationEntityRepository reservationEntityRepository;
    private final FeignClient feignClient;

    @Autowired
    public ReservationService(ReservationEntityRepository reservationEntityRepository, FeignClient feignClient){
        this.reservationEntityRepository = reservationEntityRepository;
        this.feignClient = feignClient;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public FindReservationResult createReservation(ReservationCreatedCommand command) {
        Reservation reservation = Reservation.builder()
                .lendingId(command.getLendingId())
                .uid(command.getUid())
                .bookId(command.getBookId())
                .libraryId(command.getLibraryId())
                .libraryName(command.getLibraryName())
                .reservationDateTime(command.getReservationDateTime())
                .cancelDateTime(command.getCancelDateTime())
                .build();
        ReservationEntity reservationEntity = new ReservationEntity(reservation);
        reservationEntityRepository.save(reservationEntity);

        FeignReservationRequest feignReservationCreateRequest = FeignReservationRequest.builder()
                .orderNum(reservationEntity.getOrderNum())
                .lendingId(command.getLendingId())
                .uid(command.getUid())
                .bookId(command.getBookId())
                .libraryId(command.getLibraryId())
                .libraryName(command.getLibraryName())
                .reservationDateTime(LocalDateTime.now())
                .cancelDateTime(LocalDateTime.now().plusDays(7))
                .build();

        String feignMessage = feignClient.updateReservationInfo(feignReservationCreateRequest, command.getBookId());
        System.out.println("feignMessage = " + feignMessage);
        reservation = reservationEntity.toReservation();

        return ReservationReadUseCase.FindReservationResult.findByReservation(reservation);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void deleteReservation(ReservationDeleteCommand command) {
        var findReservation = reservationEntityRepository.findById(command.getOrderNum());
        Long bookId = findReservation.get().getBookId();

        reservationEntityRepository.delete(findReservation.get());

        FeignReservationRequest feignReservationRequest = FeignReservationRequest.builder()
                .orderNum(Long.valueOf(-1))
                .bookId(bookId)
                .build();
        String feignMessage = feignClient.updateReservationInfo(feignReservationRequest, bookId);
        System.out.println("feignMessage = " + feignMessage);
    }

    @Override
    public List<FindReservationResult> getReservationListAll(Long uid) {
        var results = reservationEntityRepository.findByUid(uid);
        List<FindReservationResult> findReservationResults = new ArrayList<>();
        for (ReservationEntity reservationEntity : results){
            findReservationResults.add(FindReservationResult.findByReservation(reservationEntity.toReservation()));
        }

        return findReservationResults;
    }
}

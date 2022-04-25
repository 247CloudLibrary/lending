package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.service.ReservationOperationUseCase;
import com.cloudlibrary.lending.application.service.ReservationReadUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.ReservationCreateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.ReservationView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "예약 API")
@RequestMapping("/v1/lending/reservation")
public class ReservationController {

    private final ReservationOperationUseCase reservationOperationUseCase;
    private final ReservationReadUseCase reservationReadUseCase;

    @Autowired
    public ReservationController(ReservationOperationUseCase reservationOperationUseCase,
                          ReservationReadUseCase reservationReadUseCase) {
        this.reservationOperationUseCase = reservationOperationUseCase;
        this.reservationReadUseCase = reservationReadUseCase;
    }



    @PostMapping("")
    public ResponseEntity<ApiResponseView<ReservationView>> createReservation(@RequestBody ReservationCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }
        var command = ReservationOperationUseCase.ReservationCreatedCommand.builder()
                .lendingId(request.getLendingId())
                .uid(request.getUid())
                .bookId(request.getBookId())
                .libraryId(request.getLibraryId())
                .libraryName(request.getLibraryName())
                .reservationDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .cancelDateTime(LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .build();

        var result = reservationOperationUseCase.createReservation(command);
        return ResponseEntity.ok(new ApiResponseView<>(new ReservationView(result)));
    }


    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<ReservationView>>> getReservationsByUid(@RequestParam Long uid) {
        var results = reservationReadUseCase.getReservationListAll(uid);

        return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(ReservationView::new).collect(Collectors.toList())));
    }


    @DeleteMapping("")
    public ResponseEntity<ApiResponseView<ReservationView>> deleteByOrderNum(@RequestParam("orderNum") Long orderNum) {
        var command = ReservationOperationUseCase.ReservationDeleteCommand.builder()
                                .orderNum(orderNum)
                                .build();
        reservationOperationUseCase.deleteReservation(command);
        return ResponseEntity.noContent().build();
    }
}
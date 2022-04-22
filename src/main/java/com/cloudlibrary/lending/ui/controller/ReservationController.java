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

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "예약 API")
@RequestMapping("/v1/lending/reservation")
public class ReservationController {
    /*
    private final ReservationOperationUseCase reservationOperationUseCase;
    private final ReservationReadUseCase reservationReadUseCase;

    @Autowired
    public BookController(ReservationOperationUseCase reservationOperationUseCase,
                          ReservationReadUseCase reservationReadUseCase) {
        this.reservationOperationUseCase = reservationOperationUseCase;
        this.reservationReadUseCase = reservationReadUseCase;
    }
     */


    @PostMapping("")
    public ResponseEntity<ApiResponseView<ReservationView>> createReservation(@RequestBody ReservationCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        //return ResponseEntity.ok(new ApiResponseView<>(new ReservationView(result)));
        return ResponseEntity.ok(new ApiResponseView<>(ReservationView.builder()
                .orderNum(1L)
                .lendingId(1L)
                .uid(1L)
                .bookId(1L)
                .libraryId(1L)
                .libraryName("예약등록서초도서관")
                .reservationDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .build()
        ));
    }


    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<ReservationView>>> getReservations() {
        ReservationView reservationView1 = ReservationView.builder()
                .orderNum(2L)
                .lendingId(2L)
                .uid(2L)
                .bookId(2L)
                .libraryId(1L)
                .libraryName("예약조회도서관")
                .reservationDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .build();
        ReservationView reservationView2 = ReservationView.builder()
                .orderNum(3L)
                .lendingId(3L)
                .uid(3L)
                .bookId(3L)
                .libraryId(1L)
                .libraryName("송파도서관")
                .reservationDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .build();
        List<ReservationView> reservationView = new ArrayList<>();
        reservationView.add(reservationView1);
        reservationView.add(reservationView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(reservationView));
    }


    @DeleteMapping("")
    public ResponseEntity<ApiResponseView<ReservationView>> deleteByOrderNum(@RequestParam("orderNum") String orderNum) {
        System.out.println(orderNum);
        return ResponseEntity.ok().build();

    }
}
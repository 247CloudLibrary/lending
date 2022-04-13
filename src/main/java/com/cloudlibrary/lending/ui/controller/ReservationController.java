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

    //예약 등록
    @PostMapping("")
    public ResponseEntity<ApiResponseView<ReservationView>> createReservation(@RequestBody ReservationCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        //return ResponseEntity.ok(new ApiResponseView<>(new ReservationView(result)));
        return ResponseEntity.ok(new ApiResponseView<>(ReservationView.builder()
                .orderNum(1231L)
                .lendingId(555L)
                .uid(7700L)
                .bookId(39875238975L)
                .libraryId(154135132L)
                .libraryName("예약등록서초도서관")
                .reservationDateTime(LocalDateTime.now())
                .build()
        ));
    }

    //예약 조회
    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<ReservationView>>> getReservations() {
        ReservationView reservationView1 = ReservationView.builder()
                .orderNum(874L)
                .lendingId(55246755L)
                .uid(263547700L)
                .bookId(4362439875238975L)
                .libraryId(234562154135132L)
                .libraryName("예약조회도서관")
                .reservationDateTime(LocalDateTime.now())
                .build();
        ReservationView reservationView2 = ReservationView.builder()
                .orderNum(78456L)
                .lendingId(2351L)
                .uid(7452L)
                .bookId(1243623L)
                .libraryId(568746L)
                .libraryName("송파도서관")
                .reservationDateTime(LocalDateTime.now())
                .build();
        List<ReservationView> reservationView = new ArrayList<>();
        reservationView.add(reservationView1);
        reservationView.add(reservationView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(reservationView));
    }

    //예약 취소 한건
    @DeleteMapping("")
    public ResponseEntity<ApiResponseView<ReservationView>> deleteByOrderNum(@RequestParam("orderNum") String orderNum) {
        System.out.println(orderNum);
        return ResponseEntity.ok().build();

    }
/*
    //분실시 책에대해 전부 예약 취소
    @DeleteMapping("/{bookId}/all")
    public ResponseEntity<ApiResponseView<ReservationView>> deleteByBookId(@PathVariable("bookId") Long bookId) {

        return ResponseEntity.ok().build();

    }

 */
}

package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import com.cloudlibrary.lending.application.service.ReservationOperationUseCase;
import com.cloudlibrary.lending.application.service.ReservationReadUseCase;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.LendingCreateRequest;
import com.cloudlibrary.lending.ui.requestBody.LendingUpdateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LendingView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "대출 API")
@RequestMapping("/v1/lending")
public class LendingController {
    private final ReservationOperationUseCase reservationOperationUseCase;
    private final ReservationReadUseCase reservationReadUseCase;

    public LendingController(ReservationOperationUseCase reservationOperationUseCase,
                          ReservationReadUseCase reservationReadUseCase) {
        this.reservationOperationUseCase = reservationOperationUseCase;
        this.reservationReadUseCase = reservationReadUseCase;
    }


    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getAllOrderByAsc() {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(1L)
                .bookId(1L)
                .uid(1L)
                .libraryId(1L)
                .libraryName("그냥대출전체조회최신순")
                .lendingStatus(LendingStatus.RETURN)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("barcode2")
                .rfid("rfid2")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(2L)
                .bookId(2L)
                .uid(2L)
                .libraryId(2L)
                .libraryName("정독정독도서관")
                .lendingStatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("barcode2")
                .rfid("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseView<LendingView>> createLending(@RequestBody LendingCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }
/*
        var command = AdminOperationUseCase.AdminCreatedCommand.builder()
                .rid(request.getRid())
                .libraryId(request.getLibraryId())
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .thumbnailImage(request.getThumbnailImage())
                .coverImage(request.getCoverImage())
                .build();

        var result = AdminOperationUseCase.createAdmin(command);
*/
        //return ResponseEntity.ok(new ApiResponseView<>(new AdminView(result)));
        return ResponseEntity.ok(new ApiResponseView<>(LendingView.builder()
                .lendingId(3L)
                .bookId(3L)
                .uid(3L)
                .libraryId(3L)
                .libraryName("대출등록합니다")
                .lendingStatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("barcode1")
                .rfid("rfid1")
                .build()
        ));
    }

    //반납
    @PatchMapping("")
    public ResponseEntity<ApiResponseView<LendingView>> updateLendingReturn(@RequestParam("uid") Long uid, @RequestParam("lendingStatus") String lendingStatus) {

        return ResponseEntity.ok(new ApiResponseView<>(LendingView.builder()
                .lendingId(1L)
                .bookId(1L)
                .uid(1L)
                .libraryId(1L)
                .libraryName("반납으로 수정함")
                .lendingStatus(LendingStatus.RETURN)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfid("ytuwtwkjeq9")
                .build()
        ));
    }

}

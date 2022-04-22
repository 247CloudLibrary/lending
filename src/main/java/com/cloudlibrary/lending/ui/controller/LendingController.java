package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.service.*;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.LendingCreateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LendingView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "대출 API")
@RequestMapping("/v1/lending")
public class LendingController {
    private final LendingOperationUseCase lendingOperationUseCase;
    private final LendingReadUseCase lendingReadUseCase;

    public LendingController(LendingOperationUseCase lendingOperationUseCase,
                             LendingReadUseCase lendingReadUseCase) {
        this.lendingOperationUseCase = lendingOperationUseCase;
        this.lendingReadUseCase = lendingReadUseCase;
    }




    @PostMapping("")
    public ResponseEntity<ApiResponseView<LendingView>> createLending(@RequestBody LendingCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        var command = LendingOperationUseCase.LendingCreateCommand.builder()
                .bookId(request.getBookId())
                .uid(request.getUid())
                .libraryId(request.getLibraryId())
                .libraryName(request.getLibraryName())
                .lendingStatus("OUT")
                .lendingDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .returnDateTime("0")
                .barcode(request.getBarcode())
                .rfid(request.getRfid())
                .build();
        var result = lendingOperationUseCase.createAdmin(command);

        //TODO : feign


        return ResponseEntity.ok(new ApiResponseView<>(new LendingView(result)));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getAllLendingOrderByTimeDesc() {
        var results = lendingReadUseCase.getAllLendingOrderByTimeDesc();

        return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(LendingView::new).collect(Collectors.toList())));

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
                .lendingStatus("RETURN")
                .lendingDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .returnDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .barcode("34235kfjs3")
                .rfid("ytuwtwkjeq9")
                .build()
        ));
    }

}

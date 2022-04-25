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
                .returnDateTime(LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .barcode(request.getBarcode())
                .rfid(request.getRfid())
                .build();
        var result = lendingOperationUseCase.createLending(command);

        return ResponseEntity.ok(new ApiResponseView<>(new LendingView(result)));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getAllLendingOrderByTimeDesc() {
        var results = lendingReadUseCase.getAllLendingOrderByTimeDesc();

        return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(LendingView::new).collect(Collectors.toList())));

    }

    @PatchMapping("")
    public ResponseEntity<ApiResponseView<LendingView>> updateLending(@RequestParam("lendingId") Long lendingId, @RequestParam("lendingStatus") String lendingStatus) {

        var command = LendingOperationUseCase.LendingUpdateCommand.builder()
                .lendingId(lendingId)
                .lendingStatus(lendingStatus)
                .build();
        var result = lendingOperationUseCase.updateLending(command);

        return ResponseEntity.ok(new ApiResponseView<>(new LendingView(result)));
    }

}

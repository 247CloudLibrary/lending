package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.domain.LibrariesRules;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.LibrariesRulesCreateRequest;
import com.cloudlibrary.lending.ui.requestBody.LibrariesRulesUpdateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LibrariesRulesView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "도서관규정 API")
@RequestMapping("/v1/lending/libraries-rules")
public class LibrariesRulesController {
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

    //유저 대출 가능 여부 조회
    @GetMapping("")
    public ResponseEntity<ApiResponseView<Boolean>> getPossible(@RequestParam("uid") Long uid) {
        //var query = new AdminReadUseCase.AdminFindQuery(id);

        //var result = AdminReadUseCase.getAdmin(query);

        //return ResponseEntity.ok(new ApiResponseView<>(new AdminView(result)));
        return ResponseEntity.ok(new ApiResponseView<>(true));
    }

    //도서관제재 등록
    @PatchMapping("")
    public ResponseEntity<ApiResponseView<LibrariesRulesView>> createRules(@RequestBody LibrariesRulesCreateRequest request) {
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
        return ResponseEntity.ok(new ApiResponseView<>(LibrariesRulesView.builder()
                .libraryId(3L)
                .libraryName("도서관제재 등록")
                .lendingAvailableCount(5)
                .lengdingAvailableDays(7)
                .overdueCount(6)
                .longtermOverdueDays(3)
                .lendingLimitDays(2)
                .build()
        ));
    }
}

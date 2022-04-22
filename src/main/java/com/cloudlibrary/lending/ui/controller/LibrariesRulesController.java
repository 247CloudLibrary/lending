package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.domain.LibrariesRules;
import com.cloudlibrary.lending.application.service.LibrariesRulesOperationUseCase;
import com.cloudlibrary.lending.application.service.LibrariesRulesReadUseCase;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.LibrariesRulesCreateRequest;
import com.cloudlibrary.lending.ui.requestBody.LibrariesRulesUpdateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LibrariesRulesView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "도서관규정 API")
@RequestMapping("/v1/lending")
public class LibrariesRulesController {

    private final LibrariesRulesOperationUseCase librariesRulesOperationUseCase;
    private final LibrariesRulesReadUseCase librariesRulesReadUseCase;

    @Autowired
    public LibrariesRulesController(LibrariesRulesOperationUseCase librariesRulesOperationUseCase,
                          LibrariesRulesReadUseCase librariesRulesReadUseCase) {
        this.librariesRulesOperationUseCase = librariesRulesOperationUseCase;
        this.librariesRulesReadUseCase = librariesRulesReadUseCase;
    }

    @GetMapping("/libraries-rules")
    public ResponseEntity<ApiResponseView<Boolean>> getUserLendingPossible(@RequestParam("uid") Long uid, @RequestParam("libraryId") Long libraryId) {
        //TODO : uid로 블랙리스트인지 아닌지 + uid로 대출한 횟수 조회한 뒤 도서관 제한숫자보다 크다 적다 봐야, 도서관 id필요

        return ResponseEntity.ok(new ApiResponseView<>(true));
    }

    @PatchMapping("/libraries-rules")
    public ResponseEntity<ApiResponseView<LibrariesRulesView>> createRules(@RequestBody LibrariesRulesCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        var command = LibrariesRulesOperationUseCase.LibrariesRulesCreatedCommand.builder()
                .libraryId(request.getLibraryId())
                .libraryName(request.getLibraryName())
                .lendingAvailableCount(request.getLendingAvailableCount())
                .lengdingAvailableDays(request.getLengdingAvailableDays())
                .overdueCount(request.getOverdueCount())
                .longtermOverdueDays(request.getLongtermOverdueDays())
                .lendingLimitDays(request.getLendingLimitDays())
                .build();

        var result = librariesRulesOperationUseCase.createLibrariesRules(command);

        return ResponseEntity.ok(new ApiResponseView<>(new LibrariesRulesView(result)));
    }

    @PatchMapping("/libraries-rules-withdraw")
    public ResponseEntity<ApiResponseView<LibrariesRulesView>> deleteRules(@RequestBody LibrariesRulesUpdateRequest request) {
        var command = LibrariesRulesOperationUseCase.LibrariesRulesDeleteCommand.builder()
                .libraryId((request.getLibraryId()))
                .build();

        librariesRulesOperationUseCase.deleteLibrariesRules(command);

        return ResponseEntity.noContent().build();
    }
}
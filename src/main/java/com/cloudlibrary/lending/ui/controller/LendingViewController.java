package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.service.LendingReadUseCase;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LendingView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "대출 API")
@RequestMapping("/v1/view")
public class LendingViewController {

    private final LendingReadUseCase lendingReadUseCase;

    public LendingViewController(LendingReadUseCase lendingReadUseCase) {
        this.lendingReadUseCase = lendingReadUseCase;
    }


    @GetMapping("/auth-lending")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getMember(@RequestParam("uid") Long uid) {
        var query = new LendingReadUseCase.LendingFindQueryByUid(uid);
        var results = lendingReadUseCase.getLendingByUid(query);
        return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(LendingView::new).collect(Collectors.toList())));
    }
}

package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LendingView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "대출 API")
@RequestMapping("/view")
public class LendingViewController {

    @GetMapping("/auth-lending")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getMember(@RequestParam("uid") Long uid) {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(1L)
                .bookId(1L)
                .uid(1L)
                .libraryId(1L)
                .libraryName("회원별 대출 전체 조회")
                .lendingStatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .returnDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .barcode("fhs23a234d")
                .rfid("546745ysertg")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(2L)
                .bookId(2L)
                .uid(1L)
                .libraryId(1L)
                .libraryName("도서관")
                .lendingStatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .returnDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                .barcode("34235kfjs3")
                .rfid("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }
}

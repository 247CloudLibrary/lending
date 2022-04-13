package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.LendingCreateRequest;
import com.cloudlibrary.lending.ui.requestBody.LendingUpdateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LendingView;
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
@Api(value = "대출 API")
@RequestMapping("/v1/lending")
public class LendingController {
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

    //대출 전체 조회 최신순
    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getAll() {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(35134L)
                .bookId(512412L)
                .uid(31213423L)
                .libraryId(1352413L)
                .libraryName("그냥대출전체조회최신순")
                .lendingstatus(LendingStatus.RETURN)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("fhs23a234d")
                .rfId("546745ysertg")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("정독정독도서관")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }

    //회원별 대출 전체 조회
    @GetMapping("/view/auth-lending")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getMember(@RequestParam("uid") Long uid) {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(35134L)
                .bookId(512412L)
                .uid(31213423L)
                .libraryId(1352413L)
                .libraryName("회원별 대출 전체 조회")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("fhs23a234d")
                .rfId("546745ysertg")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("정독정독도서관")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }
/*
    //회원별 대출 조회
    @GetMapping("/{uid}/out")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getOut(@PathVariable("uid") Long uid) {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(35134L)
                .bookId(512412L)
                .uid(31213423L)
                .libraryId(1352413L)
                .libraryName("회원별 대출중")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("fhs23a234d")
                .rfId("546745ysertg")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("정독정독도서관")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }

    //회원별 반납 조회
    @GetMapping("/{uid}/return")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getReturn(@PathVariable("uid") Long uid) {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(35134L)
                .bookId(512412L)
                .uid(31213423L)
                .libraryId(1352413L)
                .libraryName("회원별 반납 조회")
                .lendingstatus(LendingStatus.RETURN)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("fhs23a234d")
                .rfId("546745ysertg")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("정독정독도서관")
                .lendingstatus(LendingStatus.RETURN)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }

    //회원별 연체 조회
    @GetMapping("/{uid}/overdue")
    public ResponseEntity<ApiResponseView<List<LendingView>>> getOverdue(@PathVariable("uid") Long uid) {
        LendingView lendingView1 = LendingView.builder()
                .lendingId(35134L)
                .bookId(512412L)
                .uid(31213423L)
                .libraryId(1352413L)
                .libraryName("회원별 연체 조회")
                .lendingstatus(LendingStatus.OVERDUE)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("fhs23a234d")
                .rfId("546745ysertg")
                .build();
        LendingView lendingView2 = LendingView.builder()
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("정독정독도서관")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build();
        List<LendingView> lendingView = new ArrayList<>();
        lendingView.add(lendingView1);
        lendingView.add(lendingView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(lendingView));
    }
    */

    //대출등록
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
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("대출등록합니다")
                .lendingstatus(LendingStatus.OUT)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build()
        ));
    }

    //반납
    @PatchMapping("")
    public ResponseEntity<ApiResponseView<LendingView>> updateLendingReturn(@RequestParam("uid") Long uid, @RequestParam("lendingStatus") String lendingStatus) {

        return ResponseEntity.ok(new ApiResponseView<>(LendingView.builder()
                .lendingId(72335134L)
                .bookId(9878512412L)
                .uid(554221213423L)
                .libraryId(985435243L)
                .libraryName("반납으로 수정함")
                .lendingstatus(LendingStatus.RETURN)
                .lendingDateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now())
                .barcode("34235kfjs3")
                .rfId("ytuwtwkjeq9")
                .build()
        ));
    }

}

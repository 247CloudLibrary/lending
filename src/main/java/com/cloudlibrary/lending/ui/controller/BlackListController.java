package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.BlackListCreateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.BlackListView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Api(value = "블랙리스트 API")
@RequestMapping("/v1/lending/blacklist")
public class BlackListController {
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

    //블랙리스트 등록
    @PostMapping("")
    public ResponseEntity<ApiResponseView<BlackListView>> createBlackList(@RequestBody BlackListCreateRequest request) {
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
        //return ResponseEntity.ok(new ApiResponseView<>(new BlackListView(result)));
        /*return ResponseEntity.ok(new ApiResponseView<>(BlackListView.builder()
                .uid(1)
                .libraryId(123)
                .libraryName("개포")
                .build()
        ));

         */
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    //도서관별 조회
    @GetMapping("/{libraryId}")
    public ResponseEntity<ApiResponseView<List<BlackListView>>> getBlackLists(@PathVariable("libraryId") String libraryId) {
        BlackListView blackListView1 = BlackListView.builder()
                .uid(1L)
                .libraryId(123L)
                .libraryName("개포")
                .build();
        BlackListView blackListView2 = BlackListView.builder()
                .uid(2L)
                .libraryId(234L)
                .libraryName("역삼")
                .build();
        List<BlackListView> blackListView = new ArrayList<>();
        blackListView.add(blackListView1);
        blackListView.add(blackListView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(blackListView));
    }

    //도서관+회원
    @GetMapping("/{libraryId}/{uid}")
    public ResponseEntity<ApiResponseView<List<BlackListView>>> getBlackLists(@PathVariable("libraryId") Long libraryId, @PathVariable("uid") Long uid) {
        BlackListView blackListView1 = BlackListView.builder()
                .uid(1L)
                .libraryId(123L)
                .libraryName("도서관과회원")
                .build();
        BlackListView blackListView2 = BlackListView.builder()
                .uid(2L)
                .libraryId(234L)
                .libraryName("한꺼번에")
                .build();
        List<BlackListView> blackListView = new ArrayList<>();
        blackListView.add(blackListView1);
        blackListView.add(blackListView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(blackListView));
    }

    //블랙리스트 삭제
    @DeleteMapping("/{uid}")
    public ResponseEntity<ApiResponseView<BlackListView>> deleteBlackList(@PathVariable("uid") String uid) {

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

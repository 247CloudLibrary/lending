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
    @PatchMapping("")
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


    //블랙리스트 조회
    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<BlackListView>>> getBlackLists(@RequestParam("uid") Long uid, @RequestParam("libraryId") Long libraryId) {
        BlackListView blackListView1 = BlackListView.builder()
                .uid(1L)
                .libraryId(123L)
                .libraryName("개포블랙")
                .build();
        BlackListView blackListView2 = BlackListView.builder()
                .uid(2L)
                .libraryId(234L)
                .libraryName("역삼블랙")
                .build();
        List<BlackListView> blackListView = new ArrayList<>();
        blackListView.add(blackListView1);
        blackListView.add(blackListView2);

        //return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(AdminView::new).collect(Collectors.toList())));
        return ResponseEntity.ok(new ApiResponseView<>(blackListView));
    }

    //블랙리스트 삭제
    @DeleteMapping("")
    public ResponseEntity<ApiResponseView<BlackListView>> deleteBlackList(@RequestParam("uid") Long uid) {
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

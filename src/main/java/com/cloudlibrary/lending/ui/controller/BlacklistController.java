package com.cloudlibrary.lending.ui.controller;

import com.cloudlibrary.lending.application.service.BlacklistOperationUseCase;
import com.cloudlibrary.lending.application.service.BlacklistReadUseCase;
import com.cloudlibrary.lending.exception.CloudLibraryException;
import com.cloudlibrary.lending.exception.MessageType;
import com.cloudlibrary.lending.ui.requestBody.BlacklistCreateRequest;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.BlacklistView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "블랙리스트 API")
@RequestMapping("/v1/lending/blacklist")
public class BlacklistController {

    private final BlacklistOperationUseCase blacklistOperationUseCase;
    private final BlacklistReadUseCase blacklistReadUseCase;

    @Autowired
    public BlacklistController(BlacklistOperationUseCase blacklistOperationUseCase,
                          BlacklistReadUseCase blacklistReadUseCase) {
        this.blacklistOperationUseCase = blacklistOperationUseCase;
        this.blacklistReadUseCase = blacklistReadUseCase;
    }

    @PutMapping("")
    public ResponseEntity<ApiResponseView<BlacklistView>> createBlacklist(@RequestBody BlacklistCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }
        var command = BlacklistOperationUseCase.BlacklistCreatedCommand.builder()
                .uid(request.getUid())
                .libraryId(request.getLibraryId())
                .libraryName(request.getLibraryName())
                .build();

        var result = blacklistOperationUseCase.createBlacklist(command);

        return ResponseEntity.ok(new ApiResponseView<>(new BlacklistView(result)));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<BlacklistView>>> getBlacklists() {
        var results = blacklistReadUseCase.getAllBlacklist();
        return ResponseEntity.ok(new ApiResponseView<>(results.stream().map(BlacklistView::new).collect(Collectors.toList())));
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponseView<BlacklistView>> deleteBlacklist(@RequestParam("uid") Long uid) {
        var command = BlacklistOperationUseCase.BlacklistDeleteCommand.builder()
                .uid(uid)
                .build();

        blacklistOperationUseCase.deleteBlacklist(command);

        return ResponseEntity.noContent().build();


    }
}

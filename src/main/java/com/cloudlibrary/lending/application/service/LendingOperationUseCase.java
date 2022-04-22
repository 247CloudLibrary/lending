package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public interface LendingOperationUseCase {

    LendingReadUseCase.FindLendingResult createAdmin(LendingCreateCommand command);

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LendingCreateCommand {
        private Long bookId;
        private Long uid;
        private Long libraryId;
        private String libraryName;

        private LendingStatus lendingStatus;

        private String lendingDateTime;
        private String returnDateTime;
        private String barcode;
        private String rfid;
    }
}

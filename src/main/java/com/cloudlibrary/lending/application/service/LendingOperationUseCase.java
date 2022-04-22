package com.cloudlibrary.lending.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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

        private String lendingStatus;

        private String lendingDateTime;
        private String returnDateTime;
        private String barcode;
        private String rfid;
    }
}

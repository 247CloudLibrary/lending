package com.cloudlibrary.lending.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface LendingOperationUseCase {

    LendingReadUseCase.FindLendingResult createLending(LendingCreateCommand command);

    LendingReadUseCase.FindLendingResult updateLending(LendingUpdateCommand command);

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

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LendingUpdateCommand {
        private Long lendingId;
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

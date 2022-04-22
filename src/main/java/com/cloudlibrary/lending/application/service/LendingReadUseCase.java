package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Lending;
import com.cloudlibrary.lending.application.domain.LendingStatus;
import lombok.*;

import java.time.LocalDateTime;

public interface LendingReadUseCase {

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class LendingFindQuery {
        private Long lendingId;

        public LendingFindQuery(Long lendingId) {
            this.lendingId = lendingId;
        }
    }

    @Setter
    @Getter
    @ToString
    @Builder
    class FindLendingResult {
        private final Long lendingId;

        private final Long bookId;
        private final Long uid;
        private final Long libraryId;
        private final String libraryName;

        private final LendingStatus lendingStatus;

        private final String lendingDateTime;
        private final String returnDateTime;
        private final String barcode;
        private final String rfid;

        public static FindLendingResult findByLending(Lending lending) {
            return FindLendingResult.builder()
                    .lendingId(lending.getLendingId())
                    .bookId(lending.getBookId())
                    .uid(lending.getUid())
                    .libraryId(lending.getLibraryId())
                    .libraryName(lending.getLibraryName())
                    .lendingStatus(lending.getLendingStatus())
                    .lendingDateTime(lending.getLendingDateTime())
                    .returnDateTime(lending.getReturnDateTime())
                    .barcode(lending.getBarcode())
                    .rfid(lending.getRfid())
                    .build();

        }
    }

}

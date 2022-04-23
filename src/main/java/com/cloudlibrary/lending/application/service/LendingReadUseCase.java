package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Lending;
import lombok.*;

import java.util.List;

public interface LendingReadUseCase {

    List<FindLendingResult> getAllLendingOrderByTimeDesc();
    List<FindLendingResult> getLendingByUid(LendingFindQueryByUid query);

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

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class LendingFindQueryByUid {
        private Long uid;

        public LendingFindQueryByUid(Long uid) {
            this.uid = uid;
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

        private final String lendingStatus;

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

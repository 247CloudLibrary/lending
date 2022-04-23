package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.LibrariesRules;
import lombok.*;

public interface LibrariesRulesReadUseCase {

    Boolean isLendingPossible(IsPossibleFindQuery query);

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class IsPossibleFindQuery {
        private Long uid;
        private Long libraryId;

        public IsPossibleFindQuery(Long uid, Long libraryId)
        {
            this.uid = uid;
            this.libraryId = libraryId;
        }
    }

    @Setter
    @Getter
    @ToString
    @Builder
    class FindLibrariesRulesResult {
        private Long libraryId;
        private String libraryName;

        private int lendingAvailableCount;
        private int lengdingAvailableDays;

        private int overdueCount;
        private int longtermOverdueDays;
        private int lendingLimitDays;

        public static FindLibrariesRulesResult findByLibrariesRules(LibrariesRules librariesRules) {
            return FindLibrariesRulesResult.builder()
                    .libraryId(librariesRules.getLibraryId())
                    .libraryName(librariesRules.getLibraryName())
                    .lendingAvailableCount(librariesRules.getLendingAvailableCount())
                    .lengdingAvailableDays(librariesRules.getLengdingAvailableDays())
                    .overdueCount(librariesRules.getOverdueCount())
                    .longtermOverdueDays(librariesRules.getLongtermOverdueDays())
                    .lendingLimitDays(librariesRules.getLendingLimitDays())
                    .build();

        }
    }
}

package com.cloudlibrary.lending.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface LibrariesRulesOperationUseCase {
    LibrariesRulesReadUseCase.FindLibrariesRulesResult createLibrariesRules(LibrariesRulesCreatedCommand librariesRulesCreatedCommand);


    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibrariesRulesCreatedCommand {
        private Long libraryId;
        private String libraryName;

        private int lendingAvailableCount;
        private int lengdingAvailableDays;

        private int overdueCount;
        private int longtermOverdueDays;
        private int lendingLimitDays;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibrariesRulesUpdateCommand {
        private Long libraryId;
        private String libraryName;

        private int lendingAvailableCount;
        private int lengdingAvailableDays;

        private int overdueCount;
        private int longtermOverdueDays;
        private int lendingLimitDays;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibrariesRulesDeleteCommand {
        private Long libraryId;
    }
}

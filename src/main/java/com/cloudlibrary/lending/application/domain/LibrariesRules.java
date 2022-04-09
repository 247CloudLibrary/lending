package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class LibrariesRules {
    private final Long libraryId;
    private final String libraryName;

    private final int lendingAvailableCount;
    private final int lengdingAvailableDays;

    private final int overdueCount;
    private final int longtermOverdueDays;
    private final int lendingLimitDays;

}

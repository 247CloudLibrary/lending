package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class LibrariesRules {
    private Long libraryId;
    private String libraryName;

    private int lendingAvailableCount;
    private int lengdingAvailableDays;

    private int overdueCount;
    private int longtermOverdueDays;
    private int lendingLimitDays;

}

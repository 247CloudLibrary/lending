package com.cloudlibrary.lending.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LibrariesRulesCreateRequest {
    private Long libraryId;
    private String libraryName;

    private int lendingAvailableCount;
    private int lengdingAvailableDays;

    private int overdueCount;
    private int longtermOverdueDays;
    private int lendingLimitDays;
}

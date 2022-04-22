package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Lending {

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
}

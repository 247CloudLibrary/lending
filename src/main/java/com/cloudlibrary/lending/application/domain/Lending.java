package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Lending {

    private final Long lendingId;

    private final Long bookId;
    private final Long uid;
    private final Long libraryId;
    private final String libraryName;

    private final LendingStatus lendingstatus;

    private final LocalDateTime lendingDateTime;
    private final LocalDateTime returnDateTime;
    private final String barcode;
    private final String rfId;
}

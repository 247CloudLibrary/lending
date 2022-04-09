package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Reservation {
    private final Long orderNum;

    private final Long lendingId;
    private final Long uid;
    private final Long bookId;
    private final Long libraryId;
    private final String libraryName;
    private final LocalDateTime reservationDateTime;
    private final LocalDateTime cancelDateTime;

}

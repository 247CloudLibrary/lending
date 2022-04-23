package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Reservation {
    private Long orderNum;

    private Long lendingId;
    private Long uid;
    private Long bookId;
    private Long libraryId;
    private String libraryName;
    private String reservationDateTime;
    private String cancelDateTime;

}

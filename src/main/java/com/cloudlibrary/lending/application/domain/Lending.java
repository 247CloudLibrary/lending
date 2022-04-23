package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Lending {

    private Long lendingId;

    private Long bookId;
    private Long uid;
    private Long libraryId;
    private String libraryName;

    private String lendingStatus;

    private String lendingDateTime;
    private String returnDateTime;
    private String barcode;
    private String rfid;
}

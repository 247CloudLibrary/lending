package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
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

    @Builder
    public Lending(Long lendingId, Long bookId, Long uid, Long libraryId, String libraryName, String lendingStatus, String lendingDateTime, String returnDateTime, String barcode, String rfid) {
        this.lendingId = lendingId;
        this.bookId = bookId;
        this.uid = uid;
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.lendingStatus = lendingStatus;
        this.lendingDateTime = lendingDateTime;
        this.returnDateTime = returnDateTime;
        this.barcode = barcode;
        this.rfid = rfid;
    }
}

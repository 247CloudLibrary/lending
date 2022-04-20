package com.cloudlibrary.lending.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.lending.application.domain.Lending;
import com.cloudlibrary.lending.application.domain.LendingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LendingEntity implements Serializable {
    private Long lendingId;

    private Long bookId;
    private Long uid;
    private Long libraryId;
    private String libraryName;

    private LendingStatus lendingStatus;

    private LocalDateTime lendingDateTime;
    private LocalDateTime returnDateTime;
    private String barcode;
    private String rfid;

    public Lending toLending() {
        return Lending.builder()
                .lendingId(getLendingId())
                .bookId(getBookId())
                .uid(getUid())
                .libraryId(getLibraryId())
                .libraryName(getLibraryName())
                .lendingStatus(getLendingStatus())
                .returnDateTime(getReturnDateTime())
                .barcode(getBarcode())
                .rfid(getRfid())
                .build();
    }

    public LendingEntity(Lending lending) {
        this.lendingId = lending.getLendingId();
        this.bookId = lending.getBookId();
        this.uid = lending.getUid();
        this.libraryId = lending.getLibraryId();
        this.libraryName = lending.getLibraryName();
        this.lendingStatus = lending.getLendingStatus();
        this.lendingDateTime = lending.getLendingDateTime();
        this.returnDateTime = lending.getReturnDateTime();
    }

}


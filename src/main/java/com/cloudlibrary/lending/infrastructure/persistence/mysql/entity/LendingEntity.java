package com.cloudlibrary.lending.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.lending.application.domain.Lending;
import com.cloudlibrary.lending.application.domain.LendingStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@DynamicUpdate
@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "lending")
public class LendingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lendingId;

    @Column(nullable = false)
    private Long bookId;
    @Column(nullable = false)
    private Long uid;
    @Column(nullable = false)
    private Long libraryId;
    @Column(nullable = false)
    private String libraryName;

    @Column(nullable = false)
    private LendingStatus lendingStatus;

    @Column(nullable = false)
    private String lendingDateTime;
    @Column(nullable = false)
    private String returnDateTime;
    @Column(nullable = false)
    private String barcode;
    @Column(nullable = false)
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
        this.barcode = lending.getBarcode();
        this.rfid = lending.getRfid();
    }

}


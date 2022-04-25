package com.cloudlibrary.lending.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.lending.application.domain.LibrariesRules;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "LibrariesRules")
public class LibrariesRulesEntity {
    @Id
    private Long libraryId;
    @Column(nullable = false)
    private String libraryName;
    @Column(nullable = false)
    private int lendingAvailableCount;
    @Column(nullable = false)
    private int lengdingAvailableDays;
    @Column(nullable = false)
    private int overdueCount;
    @Column(nullable = false)
    private int longtermOverdueDays;
    @Column(nullable = false)
    private int lendingLimitDays;

    public LibrariesRules toLibrariesRules() {
        return LibrariesRules.builder()
                .libraryId(getLibraryId())
                .libraryName(getLibraryName())
                .lendingAvailableCount(getLendingAvailableCount())
                .lengdingAvailableDays(getLengdingAvailableDays())
                .overdueCount(getOverdueCount())
                .longtermOverdueDays(getLongtermOverdueDays())
                .lendingLimitDays(getLendingLimitDays())
                .build();
    }

    public LibrariesRulesEntity(LibrariesRules librariesRules) {
        this.libraryId = librariesRules.getLibraryId();
        this.libraryName = librariesRules.getLibraryName();
        this.lendingAvailableCount = librariesRules.getLendingAvailableCount();
        this.lengdingAvailableDays = librariesRules.getLengdingAvailableDays();
        this.overdueCount = librariesRules.getOverdueCount();
        this.longtermOverdueDays = librariesRules.getLongtermOverdueDays();
        this.lendingLimitDays = librariesRules.getLendingLimitDays();
    }
}

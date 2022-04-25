package com.cloudlibrary.lending.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.lending.application.domain.Reservation;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNum;
    @Column(nullable = false)
    private Long lendingId;
    @Column(nullable = false)
    private Long uid;
    @Column(nullable = false)
    private Long bookId;
    @Column(nullable = false)
    private Long libraryId;
    @Column(nullable = false)
    private String libraryName;
    @Column(nullable = false)
    private String reservationDateTime;
    @Column(nullable = false)
    private String cancelDateTime;

    public Reservation toReservation() {
        return Reservation.builder()
                .orderNum(getOrderNum())
                .lendingId(getLendingId())
                .uid(getUid())
                .bookId(getBookId())
                .libraryId(getLibraryId())
                .libraryName(getLibraryName())
                .reservationDateTime(getReservationDateTime())
                .cancelDateTime(getCancelDateTime())
                .build();
    }

    public ReservationEntity(Reservation reservation) {
        this.orderNum = reservation.getOrderNum();
        this.lendingId = reservation.getLendingId();
        this.uid = reservation.getUid();
        this.bookId = reservation.getBookId();
        this.libraryId = reservation.getLibraryId();
        this.libraryName = reservation.getLibraryName();
        this.reservationDateTime = reservation.getReservationDateTime();
        this.cancelDateTime = reservation.getCancelDateTime();
    }
}

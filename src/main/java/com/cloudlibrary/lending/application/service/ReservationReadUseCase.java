package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Reservation;
import lombok.*;

import java.util.List;

public interface ReservationReadUseCase {

    List<FindReservationResult> getReservationListAll(Long uid);

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class ReservationFindQuery {
        private Long orderNum;

        public ReservationFindQuery(Long orderNum) {
            this.orderNum = orderNum;
        }
    }

    @Setter
    @Getter
    @ToString
    @Builder
    class FindReservationResult {
        private Long orderNum;

        private Long lendingId;
        private Long uid;
        private Long bookId;
        private Long libraryId;
        private String libraryName;
        private String reservationDateTime;
        private String cancelDateTime;

        public static FindReservationResult findByReservation(Reservation reservation) {
            return FindReservationResult.builder()
                    .orderNum(reservation.getOrderNum())
                    .lendingId(reservation.getLendingId())
                    .uid(reservation.getUid())
                    .bookId(reservation.getBookId())
                    .libraryId(reservation.getLibraryId())
                    .libraryName(reservation.getLibraryName())
                    .reservationDateTime(reservation.getReservationDateTime())
                    .cancelDateTime(reservation.getCancelDateTime())
                    .build();
        }
    }
}

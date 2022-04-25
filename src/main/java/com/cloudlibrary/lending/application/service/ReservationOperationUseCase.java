package com.cloudlibrary.lending.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface ReservationOperationUseCase {
    ReservationReadUseCase.FindReservationResult createReservation(ReservationCreatedCommand command);

    void deleteReservation(ReservationDeleteCommand command);


    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class ReservationCreatedCommand {
        private Long lendingId;
        private Long uid;
        private Long bookId;
        private Long libraryId;
        private String libraryName;
        private String reservationDateTime;
        private String cancelDateTime;

    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class ReservationUpdateCommand {
        private Long lendingId;
        private Long uid;
        private Long bookId;
        private Long libraryId;
        private String libraryName;
        private String reservationDateTime;
        private String cancelDateTime;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class ReservationDeleteCommand {
        private Long orderNum;
    }
}

package com.cloudlibrary.lending.ui.requestBody;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FeignReservationRequest {
    private Long orderNum;
    private Long lendingId;
    private Long uid;
    private Long bookId;
    private Long libraryId;
    private String libraryName;
    private LocalDateTime reservationDateTime;
    private LocalDateTime cancelDateTime;
}

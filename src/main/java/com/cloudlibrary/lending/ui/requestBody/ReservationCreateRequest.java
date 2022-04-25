package com.cloudlibrary.lending.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationCreateRequest {

    private Long lendingId;
    private Long uid;
    private Long bookId;
    private Long libraryId;
    private String libraryName;
}

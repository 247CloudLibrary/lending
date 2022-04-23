package com.cloudlibrary.lending.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LendingCreateRequest {

    private Long bookId;
    private Long uid;
    private Long libraryId;
    private String libraryName;

    private String barcode;
    private String rfid;

}
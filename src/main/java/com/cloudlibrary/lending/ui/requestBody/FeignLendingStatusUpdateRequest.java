package com.cloudlibrary.lending.ui.requestBody;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FeignLendingStatusUpdateRequest {
    private String lendingStatus;
}

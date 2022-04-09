package com.cloudlibrary.lending.ui.view.lending;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LendingView {
    @ApiModelProperty(value = "대출 PK")
    private final Long lendingId;
    @ApiModelProperty(value = "책 아이디")
    private final Long bookId;
    @ApiModelProperty(value = "유저 아이디")
    private final Long uid;
    @ApiModelProperty(value = "도서관 아이디")
    private final Long libraryId;
    @ApiModelProperty(value = "도서관 이름")
    private final String libraryName;

    @ApiModelProperty(value = "대출 상태")
    private final LendingStatus lendingstatus;
    @ApiModelProperty(value = "대출 일시")
    private final LocalDateTime lendingDateTime;
    @ApiModelProperty(value = "반납 예정 일시")
    private final LocalDateTime returnDateTime;
    @ApiModelProperty(value = "바코드")
    private final String barcode;
    @ApiModelProperty(value = "rfid")
    private final String rfId;

}

package com.cloudlibrary.lending.ui.view.lending;

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
public class ReservationView {
    @ApiModelProperty(value = "예약 순번")
    private final Long orderNum;

    @ApiModelProperty(value = "대출 PK")
    private final Long lendingId;
    @ApiModelProperty(value = "유저 아이디")
    private final Long uid;
    @ApiModelProperty(value = "책 아이디")
    private final Long bookId;

    @ApiModelProperty(value = "도서관 아이디")
    private final Long libraryId;
    @ApiModelProperty(value = "도서관 이름")
    private final String libraryName;
    @ApiModelProperty(value = "예약 일시")
    private final String reservationDateTime;
    @ApiModelProperty(value = "예약 취소 예정 일시")
    private final String cancelDateTime;

}

package com.cloudlibrary.lending.ui.view.lending;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import com.cloudlibrary.lending.application.service.LendingReadUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
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
    private final LendingStatus lendingStatus;
    @ApiModelProperty(value = "대출 일시")
    private final String lendingDateTime;
    @ApiModelProperty(value = "반납 예정 일시")
    private final String returnDateTime;
    @ApiModelProperty(value = "바코드")
    private final String barcode;
    @ApiModelProperty(value = "rfid")
    private final String rfid;

    public LendingView(LendingReadUseCase.FindLendingResult result){
        this.lendingId = result.getLendingId();

        this.bookId = result.getBookId();
        this.uid = result.getUid();
        this.libraryId = result.getLibraryId();
        this.libraryName = result.getLibraryName();

        this.lendingStatus = result.getLendingStatus();

        this.lendingDateTime = result.getLendingDateTime();
        this.returnDateTime = result.getReturnDateTime();
        this.barcode = result.getBarcode();
        this.rfid = result.getRfid();
    }


}

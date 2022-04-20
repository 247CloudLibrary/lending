package com.cloudlibrary.lending.ui.view.lending;

import com.cloudlibrary.lending.application.service.LibrariesRulesReadUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibrariesRulesView {
    @ApiModelProperty(value = "도서관 아이디")
    private final Long libraryId;
    @ApiModelProperty(value = "도서관 이름")
    private final String libraryName;

    @ApiModelProperty(value = "대출 가능 권수")
    private final int lendingAvailableCount;
    @ApiModelProperty(value = "대출 기간 날 수")
    private final int lengdingAvailableDays;


    @ApiModelProperty(value = "대출 제한 <- 연체 횟수")
    private final int overdueCount;
    @ApiModelProperty(value = "장기 연체 구분 일수")
    private final int longtermOverdueDays;
    @ApiModelProperty(value = "대출제한 일수")
    private final int lendingLimitDays;

    public LibrariesRulesView(LibrariesRulesReadUseCase.FindLibrariesRulesResult result) {

        this.libraryId = result.getLibraryId();
        this.libraryName = result.getLibraryName();
        this.lendingAvailableCount = result.getLendingAvailableCount();
        this.lengdingAvailableDays = result.getLengdingAvailableDays();
        this.overdueCount = result.getOverdueCount();
        this.longtermOverdueDays = result.getLongtermOverdueDays();
        this.lendingLimitDays = result.getLendingLimitDays();
    }

}

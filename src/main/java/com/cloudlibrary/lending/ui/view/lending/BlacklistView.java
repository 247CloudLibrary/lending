package com.cloudlibrary.lending.ui.view.lending;

import com.cloudlibrary.lending.application.service.BlacklistReadUseCase;
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
public class BlacklistView {
    @ApiModelProperty(value = "블랙리스트 아이디")
    private final Long blacklistId;
    @ApiModelProperty(value = "유저 아이디")
    private final Long uid;
    @ApiModelProperty(value = "도서관 아이디")
    private final Long libraryId;
    @ApiModelProperty(value = "도서관 이름")
    private final String libraryName;

    public BlacklistView(BlacklistReadUseCase.FindBlacklistResult result) {
        this.blacklistId = result.getBlacklistId();
        this.uid = result.getUid();
        this.libraryId = result.getLibraryId();
        this.libraryName = result.getLibraryName();
    }
}

package com.cloudlibrary.lending.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface BlacklistOperationUseCase {

    BlacklistReadUseCase.FindBlacklistResult createBlacklist(BlacklistCreatedCommand command);

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class BlacklistCreatedCommand {
        private Long uid;
        private Long libraryId;
        private String libraryName;
    }
}

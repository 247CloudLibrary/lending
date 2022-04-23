package com.cloudlibrary.lending.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface BlacklistOperationUseCase {

    BlacklistReadUseCase.FindBlacklistResult createBlacklist(BlacklistCreatedCommand command);

    void deleteBlacklist(BlacklistDeleteCommand command);

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class BlacklistCreatedCommand {
        private Long uid;
        private Long libraryId;
        private String libraryName;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class BlacklistDeleteCommand {
        private Long uid;
    }
}

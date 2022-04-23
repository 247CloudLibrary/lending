package com.cloudlibrary.lending.application.service;

import com.cloudlibrary.lending.application.domain.Blacklist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public interface BlacklistReadUseCase {

    List<FindBlacklistResult> getAllBlacklist();

    @Setter
    @Getter
    @ToString
    @Builder
    class FindBlacklistResult{
        private Long blacklistId;
        private Long uid;
        private Long libraryId;
        private String libraryName;

        public static FindBlacklistResult findByBlacklist(Blacklist blacklist){
            return FindBlacklistResult.builder()
                    .blacklistId(blacklist.getBlacklistId())
                    .uid(blacklist.getUid())
                    .libraryId(blacklist.getLibraryId())
                    .libraryName(blacklist.getLibraryName())
                    .build();
        }
    }
}

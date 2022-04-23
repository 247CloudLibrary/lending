package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Blacklist {
    private Long blacklistId;
    private Long uid;
    private Long libraryId;
    private String libraryName;

}

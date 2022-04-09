package com.cloudlibrary.lending.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BlackList {
    private final Long uid;
    private final Long libraryId;
    private final String libraryName;

}

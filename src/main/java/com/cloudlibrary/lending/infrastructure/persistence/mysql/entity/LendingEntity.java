package com.cloudlibrary.lending.infrastructure.persistence.mysql.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LendingEntity implements Serializable {
    private Long id;
    private String rid;
    private Long libraryId;
    private String isbn;
    private String title;
    private String thumbnailImage;
    private String coverImage;
/*
    public Lending toLending() {
        return Lending.builder()
                .id(this.id)
                .rid(this.rid)
                .libraryId(this.libraryId)
                .isbn(this.isbn)
                .title(this.title)
                .thumbnailImage(this.thumbnailImage)
                .coverImage(this.coverImage)
                .build();
    }

    public LendingEntity(Lending lending) {
        this.id = lending.getId();
        this.rid = lending.getRid();
        this.libraryId = lending.getLibraryId();
        this.isbn = lending.getIsbn();
        this.title = lending.getTitle();
        this.thumbnailImage = lending.getThumbnailImage();
        this.coverImage = lending.getCoverImage();
    }
*/
}


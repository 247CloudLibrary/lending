package com.cloudlibrary.lending.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.lending.application.domain.Blacklist;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@DynamicUpdate
@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "blacklist")
public class BlacklistEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blacklistId;
    @Column(nullable = false)
    private Long uid;
    @Column(nullable = false)
    private Long libraryId;
    @Column(nullable = false)
    private String libraryName;

    public Blacklist toBlacklist() {
        return Blacklist.builder()
                .blacklistId(getBlacklistId())
                .uid(getUid())
                .libraryId(getLibraryId())
                .libraryName(getLibraryName())
                .build();
    }

    public BlacklistEntity(Blacklist blacklist) {
        this.blacklistId = blacklist.getBlacklistId();
        this.uid = blacklist.getUid();
        this.libraryId = blacklist.getLibraryId();
        this.libraryName = blacklist.getLibraryName();
    }

}

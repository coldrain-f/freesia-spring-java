package edu.coldrain.entity;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends Timestamped {

    @Id
    @Column(name = "AUTHORITY_NAME", length = 50)
    private String authorityName;

    @Builder
    public Authority(final String authorityName) {
        this.authorityName = authorityName;
    }
}

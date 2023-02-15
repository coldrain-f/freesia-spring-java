package edu.coldrain.entity;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "HISTORY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column
    private Long numberOfReads;

    @Column
    private LocalDateTime progressTime;

    @Column
    private LocalDateTime completedAt;

    @Builder
    public History(final User user, final Category category, final Long numberOfReads, final LocalDateTime progressTime,
                   final LocalDateTime completedAt) {
        this.user = user;
        this.category = category;
        this.numberOfReads = numberOfReads;
        this.progressTime = progressTime;
        this.completedAt = completedAt;
    }

    public void changeUser(final User user) {
        this.user = user;
    }

    public void changeCategory(final Category category) {
        this.category = category;
    }

    public void changeNumberOfReads(final Long numberOfReads) {
        this.numberOfReads = numberOfReads;
    }

    public void changeProgressTime(final LocalDateTime progressTime) {
        this.progressTime = progressTime;
    }

    public void changeCompletedAt(final LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}

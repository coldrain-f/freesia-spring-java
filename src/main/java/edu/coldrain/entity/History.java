package edu.coldrain.entity;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
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
}

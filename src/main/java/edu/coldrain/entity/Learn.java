package edu.coldrain.entity;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Learn extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LEARN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public Learn(final User user, final Category category) {
        this.user = user;
        this.category = category;
    }

    public void changeUser(final User user) {
        this.user = user;
    }

    public void changeCategory(final Category category) {
        this.category = category;
    }
}

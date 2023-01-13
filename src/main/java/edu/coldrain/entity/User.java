package edu.coldrain.entity;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private Long id;

    private String username;

    private String password;

    private String email;

    private String role;

    @Builder
    public User(final String username, final String password, final String email, final String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void changePassword(final String password) {
        this.password = password;
    }

    public void changeRole(final String role) {
        this.role = role;
    }
}

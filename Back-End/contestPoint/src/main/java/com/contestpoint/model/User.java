package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private Long ruId;

    @Column(name="first_name") @NonNull
    private String firstName;

    @Column(name="last_name") @NonNull
    private String lastName;

    @Column(name="email") @NonNull
    private String email;

    @Column(name="password") @NonNull
    private String password;

    @Column(name="institution_name")
    private String institutionName;

    @Column(name="profile_picture")

    private String profilePicture;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    List<UserLike> userLikes;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    List<ParticipationContract> contracts;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    List<Contest> contests;
}

package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "RegularUser")
public class RegularUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ru_id")
    private Long ruId;

    @Column(name="first_name") @NonNull
    private String firstName;

    @Column(name="last_name") @NonNull
    private String lastName;

    @Column(name="email") @NonNull
    private String email;

    @Column(name="password") @NonNull
    private String password;

    @Column(name="profile_picture")

    private String profilePicture;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "regularUser")
    List<Like> likes;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "regularUser")
    List<ParticipationContract> contracts;
}

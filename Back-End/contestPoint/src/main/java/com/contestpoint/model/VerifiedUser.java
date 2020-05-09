package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "VerifiedUser")
public class VerifiedUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="vu_id")
    private Long vuId;

    @Column(name="institutionName") @NonNull
    private String institutionName;

    @Column(name="cui") @NonNull
    private String cui;

    @Column(name="email") @NonNull
    private String email;

    @Column(name="password") @NonNull
    private String password;

    @Column(name="profile_picture")
    private String profilePicture;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "verifiedUser")
    List<Contest> contests;
}

package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Contest")
public class Contest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="contest_id")
    private Long contestId;

    @Column(name="contest_name") @NonNull
    private String contest_name;

    @Column(name="details") @NonNull
    private String details;

    @Column(name="partners")
    private String partners;

    @Column(name="enrollment_start") @NonNull
    private Date enrollmentStart;

    @Column(name="enrollment_due") @NonNull
    private Date enrollmentDue;

    @Column(name="start_date") @NonNull
    private Date startDate;

    @Column(name="end_date") @NonNull
    private Date endDate;

    @Column(name="cover_picture")
    private String coverPicture;

    @ManyToOne
    @JoinColumn(name="vu_id", nullable=false)
    private VerifiedUser verifiedUser;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "contest")
    List<Like> likes;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "contest")
    List<ParticipationContract> contracts;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "contest")
    List<TagContest> tagContests;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "contest")
    List<LocationContest> locationContests;

    @OneToOne
    @JoinColumn(name="quiz_id", nullable=false)
    private Quiz quiz;
}

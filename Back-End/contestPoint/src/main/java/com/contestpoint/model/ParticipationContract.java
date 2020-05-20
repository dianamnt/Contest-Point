package com.contestpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ParticipationContract")
public class ParticipationContract {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="pc_id")
    private Long pcId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "contract")
    List<Detail> details;
}

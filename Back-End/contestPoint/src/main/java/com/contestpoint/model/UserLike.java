package com.contestpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "UserLike")
public class UserLike {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userlike_id")
    private Long userlikeId;

    @ManyToOne
    @JoinColumn(name="ru_id", nullable=false)
    private RegularUser regularUser;

    @ManyToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;
}

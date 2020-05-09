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
@Table(name = "Like")
public class Like {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name="ru_id", nullable=false)
    private RegularUser regularUser;

    @ManyToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;
}

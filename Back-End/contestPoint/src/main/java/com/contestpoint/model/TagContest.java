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
@Table(name = "TagContest")
public class TagContest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="tc_id")
    private Long tcId;

    @ManyToOne
    @JoinColumn(name="tag_id", nullable=false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;
}

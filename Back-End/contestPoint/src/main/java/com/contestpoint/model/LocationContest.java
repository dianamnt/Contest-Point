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
@Table(name = "LocationContest")
public class LocationContest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="lc_id")
    private Long lcId;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;

    @ManyToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;
}

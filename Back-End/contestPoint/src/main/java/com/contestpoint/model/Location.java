package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="location_id")
    private Long locationId;

    @Column(name="online_resource")
    private String onlineResource;

    @Column(name="street_name")
    private String streetName;

    @Column(name="street_number")
    private String streetNumber;

    @Column(name="building_number")
    private String buildingNumber;

    @Column(name="postal_code")
    private Integer postalCode;

    @Column(name="city")
    private String city;

    @Column(name="region")
    private String region;

    @Column(name="country")
    private String country;

    @Column(name="details")
    private String details;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "location")
    List<LocationContest> locationContests;
}

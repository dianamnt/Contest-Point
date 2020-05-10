package com.contestpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@JsonComponent
public class LocationDTO {
    private Long locationId;
    private String onlineResource;
    private String streetName;
    private String streetNumber;
    private String buildingNumber;
    private Integer postalCode;
    private String city;
    private String region;
    private String country;
    private String details;
}

package com.contestpoint.dto;

import com.contestpoint.model.Contest;
import com.contestpoint.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@JsonComponent
public class LocationContestDTO {
    private Long lcId;
    private Long locationId;
    private Long contestId;
}

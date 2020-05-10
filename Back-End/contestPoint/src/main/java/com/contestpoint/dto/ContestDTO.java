package com.contestpoint.dto;

import com.contestpoint.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@JsonComponent
public class ContestDTO {
    private Long contestId;
    private String contestName;
    private String details;
    private String partners;
    private Date enrollmentStart;
    private Date enrollmentDue;
    private Date startDate;
    private Date endDate;
    private String coverPicture;
    private Long userId;
}

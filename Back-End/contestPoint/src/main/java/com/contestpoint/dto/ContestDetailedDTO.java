package com.contestpoint.dto;

import com.contestpoint.model.Tag;
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
public class ContestDetailedDTO {
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
    private List<LocationDTO> locations;
    private List<TagDTO> tags;
    private List<ParticipationContractDTO> contracts;
    private List<UserLikeDTO> likes;
    private List<RequirementDTO> requirements;
}

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
public class ContractDetailedDTO {
    private Long pcId;
    private Long userId;
    private Long contestId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private List<DetailDTO> details;
}

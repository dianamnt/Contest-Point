package com.contestpoint.dto;

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
public class ParticipationContractDTO {
    private Long pcId;
    private Long userId;
    private Long contestId;
}
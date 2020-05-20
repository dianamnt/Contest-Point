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
public class RequirementDTO {
    private Long requirementId;
    private Integer orderNo;
    private String content;
    private Integer isMandatory;
    private Integer reqImage;
    private Long contestId;
}

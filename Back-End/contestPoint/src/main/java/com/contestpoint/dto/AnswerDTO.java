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
public class AnswerDTO {
    private Long answerId;
    private String textContent;
    private String imageContent;
    private Integer isCorrect;
    private Integer isChosen;
    private Long questionId;
}

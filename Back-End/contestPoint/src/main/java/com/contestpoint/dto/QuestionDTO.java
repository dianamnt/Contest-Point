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
public class QuestionDTO {
    private Long questionId;
    private String textContent;
    private String imageContent;
    private Integer score;
    private Integer isActive;
    private Long quizId;
}

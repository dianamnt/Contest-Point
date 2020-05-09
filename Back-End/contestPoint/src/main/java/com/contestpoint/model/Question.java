package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="question_id")
    private Long questionId;

    @Column(name="text_content")
    private String textContent;

    @Column(name="image_content")
    private String imageContent;

    @Column(name="score")
    private Integer score;

    @Column(name="is_active") @NonNull
    private Integer isActive;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "question")
    List<Answer> answers;

    @ManyToOne
    @JoinColumn(name="quiz_id", nullable=false)
    private Quiz quiz;
}

package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="quiz_id")
    private Long quizId;

    @Column(name="time") @NonNull
    private Integer time;

    @Column(name="total_score")
    private Integer totalScore;

    @OneToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "quiz")
    List<Question> allQuestions;
}

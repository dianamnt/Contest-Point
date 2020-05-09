package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="answer_id")
    private Long answerId;

    @Column(name="text_content")
    private String textContent;

    @Column(name="image_content")
    private String imageContent;

    @Column(name="is_correct") @NonNull
    private Integer isCorrect;

    @Column(name="is_chosen") @NonNull
    private Integer isChosen;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
}

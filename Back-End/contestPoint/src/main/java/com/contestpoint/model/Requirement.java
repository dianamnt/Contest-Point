package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Requirement")
public class Requirement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="requirement_id")
    private Long requirementId;

    @Column(name="order_no") @NonNull
    private Integer orderNo;

    @Column(name="content")
    private String content;

    @Column(name="req_image")
    private Integer reqImage;

    @Column(name="is_mandatory")
    private Integer isMandatory;

    @ManyToOne
    @JoinColumn(name="contest_id", nullable=false)
    private Contest contest;
}

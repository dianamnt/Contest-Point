package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Detail")
public class Detail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="detail_id")
    private Long detailId;

    @Column(name="order_no") @NonNull
    private Integer orderNo;

    @Column(name="text_content")
    private String textContent;

    @Column(name="image_content")
    private String imageContent;

    @ManyToOne
    @JoinColumn(name="pc_id", nullable=false)
    private ParticipationContract contract;
}

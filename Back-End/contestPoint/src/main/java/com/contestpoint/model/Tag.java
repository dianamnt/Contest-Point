package com.contestpoint.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="tag_id")
    private Long tagId;

    @Column(name="tag_name") @NonNull
    private String tagName;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "tag")
    List<TagContest> tagContests;
}

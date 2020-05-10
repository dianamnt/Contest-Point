package com.contestpoint.mapper;

import com.contestpoint.dto.TagContestDTO;
import com.contestpoint.model.Contest;
import com.contestpoint.model.Tag;
import com.contestpoint.model.TagContest;
import com.contestpoint.repository.ContestRepository;
import com.contestpoint.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagContestMapper {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ContestRepository contestRepository;

    public TagContestDTO toDTO(TagContest tagContest) {
        TagContestDTO tagContestDTO = new TagContestDTO();

        tagContestDTO.setTcId(tagContest.getTcId());
        tagContestDTO.setTagId(tagContest.getTag().getTagId());
        tagContestDTO.setContestId(tagContest.getContest().getContestId());

        return tagContestDTO;
    }

    public TagContest toEntity(TagContestDTO tagContestDTO) {
        TagContest tagContest = new TagContest();

        tagContest.setTcId(tagContestDTO.getTcId());
        Tag t = tagRepository.findById(tagContestDTO.getTagId());
        tagContest.setTag(t);
        Contest c = contestRepository.findById(tagContestDTO.getContestId());
        tagContest.setContest(c);

        return tagContest;
    }
}

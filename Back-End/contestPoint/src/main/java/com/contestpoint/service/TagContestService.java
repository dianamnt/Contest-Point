package com.contestpoint.service;

import com.contestpoint.dto.TagContestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagContestService {
    void createTagContest(TagContestDTO TagContest) throws Exception;

    void deleteTagContest(Long TagContestId);

    void updateTagContest(TagContestDTO TagContest) throws Exception;

    List<TagContestDTO> findAllTagContests();

    TagContestDTO findById(Long id);
}

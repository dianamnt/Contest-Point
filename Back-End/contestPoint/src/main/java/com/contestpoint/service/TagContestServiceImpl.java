package com.contestpoint.service;

import com.contestpoint.dto.TagContestDTO;
import com.contestpoint.mapper.TagContestMapper;
import com.contestpoint.model.TagContest;
import com.contestpoint.repository.TagContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class TagContestServiceImpl implements TagContestService{
    @Autowired
    private com.contestpoint.repository.TagContestRepository TagContestRepository;

    @Autowired
    private com.contestpoint.mapper.TagContestMapper TagContestMapper;

    @Override
    @Transactional
    public void createTagContest(TagContestDTO TagContestDTO) {
        TagContest TagContest = TagContestMapper.toEntity(TagContestDTO);
        TagContestRepository.saveData(TagContest);
    }

    @Override
    @Transactional
    public void deleteTagContest(Long TagContestId) {
        TagContestRepository.removeData(TagContestId);
    }

    @Override
    @Transactional
    public void updateTagContest(TagContestDTO TagContestDTO) {
        TagContest TagContest = TagContestMapper.toEntity(TagContestDTO);
        TagContestRepository.updateData(TagContest);
    }

    @Override
    @Transactional
    public List<TagContestDTO> findAllTagContests() {
        List<TagContestDTO> TagContestDTOList = new ArrayList<TagContestDTO>();

        for (TagContest TagContest : TagContestRepository.findAll()) {
            TagContestDTO TagContestDTO = TagContestMapper.toDTO(TagContest);
            TagContestDTOList.add(TagContestDTO);
        }

        return TagContestDTOList;
    }

    @Override
    @Transactional
    public TagContestDTO findById(Long id) {
        TagContest TagContest = TagContestRepository.findById(id);
        if (TagContest == null) {
            return null;
        }
        return TagContestMapper.toDTO(TagContest);
    }
}

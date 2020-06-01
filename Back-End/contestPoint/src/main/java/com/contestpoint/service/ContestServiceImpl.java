package com.contestpoint.service;

import com.contestpoint.dto.ContestDTO;
import com.contestpoint.dto.UserDTO;
import com.contestpoint.mapper.ContestMapper;
import com.contestpoint.model.Contest;
import com.contestpoint.model.User;
import com.contestpoint.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class ContestServiceImpl implements ContestService{
    @Autowired
    private com.contestpoint.repository.ContestRepository ContestRepository;

    @Autowired
    private com.contestpoint.mapper.ContestMapper ContestMapper;

    @Override
    @Transactional
    public ContestDTO createContest(ContestDTO contestDTO) {
        Contest contest = ContestMapper.toEntity(contestDTO);
        Long id = ContestRepository.saveData(contest);
        contest.setContestId(id);
        return ContestMapper.toDTO(contest);
    }

    @Override
    @Transactional
    public void deleteContest(Long ContestId) {
        ContestRepository.removeData(ContestId);
    }

    @Override
    @Transactional
    public void updateContest(ContestDTO ContestDTO) {
        Contest Contest = ContestMapper.toEntity(ContestDTO);
        ContestRepository.updateData(Contest);
    }

    @Override
    @Transactional
    public List<ContestDTO> findAllContests() {
        List<ContestDTO> ContestDTOList = new ArrayList<ContestDTO>();

        for (Contest Contest : ContestRepository.findAll()) {
            ContestDTO ContestDTO = ContestMapper.toDTO(Contest);
            ContestDTOList.add(ContestDTO);
        }

        return ContestDTOList;
    }

    @Override
    @Transactional
    public ContestDTO findById(Long id) {
        Contest Contest = ContestRepository.findById(id);
        if (Contest == null) {
            return null;
        }
        return ContestMapper.toDTO(Contest);
    }
}

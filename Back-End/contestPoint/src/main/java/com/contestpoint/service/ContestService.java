package com.contestpoint.service;

import com.contestpoint.dto.ContestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContestService {
    void createContest(ContestDTO Contest) throws Exception;

    void deleteContest(Long ContestId);

    void updateContest(ContestDTO Contest) throws Exception;

    List<ContestDTO> findAllContests();

    ContestDTO findById(Long id);
}

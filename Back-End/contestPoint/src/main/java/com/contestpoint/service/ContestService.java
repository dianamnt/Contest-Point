package com.contestpoint.service;

import com.contestpoint.dto.ContestDTO;
import com.contestpoint.dto.ContestDetailedDTO;
import com.contestpoint.dto.UserDTO;
import com.contestpoint.model.Contest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContestService {
    ContestDTO createContest(ContestDTO Contest) throws Exception;

    void deleteContest(Long ContestId);

    void updateContest(ContestDTO Contest) throws Exception;

    List<ContestDTO> findAllContests();

    ContestDTO findById(Long id);

    ContestDetailedDTO findByIdDetailed(Long id);

    List<ContestDetailedDTO> findAllContestDetailed();

    List<ContestDetailedDTO> findAllMyContests(Long id);

//    List<ContestDetailedDTO> findAllContestEnrolled(Long id);
//
//    List<ContestDetailedDTO> findAllContestsLiked(Long id);
}

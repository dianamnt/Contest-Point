package com.contestpoint.mapper;

import com.contestpoint.dto.ContestDTO;
import com.contestpoint.model.Contest;
import com.contestpoint.model.User;
import com.contestpoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestMapper {

    @Autowired
    private UserRepository userRepository;

    public ContestDTO toDTO(Contest contest) {
        ContestDTO contestDTO = new ContestDTO();

        contestDTO.setContestId(contest.getContestId());
        contestDTO.setContestName(contest.getContestName());
        contestDTO.setCoverPicture(contest.getCoverPicture());
        contestDTO.setDetails(contest.getDetails());
        contestDTO.setStartDate(contest.getStartDate());
        contestDTO.setEndDate(contest.getEndDate());
        contestDTO.setEnrollmentStart(contest.getEnrollmentStart());
        contestDTO.setEnrollmentDue(contest.getEnrollmentDue());
        contestDTO.setPartners(contest.getPartners());
        contestDTO.setUserId(contest.getUser().getUserId());

        return contestDTO;
    }

    public Contest toEntity(ContestDTO contest) {
        Contest contestDTO = new Contest();

        contestDTO.setContestId(contest.getContestId());
        contestDTO.setContestName(contest.getContestName());
        contestDTO.setCoverPicture(contest.getCoverPicture());
        contestDTO.setDetails(contest.getDetails());
        contestDTO.setStartDate(contest.getStartDate());
        contestDTO.setEndDate(contest.getEndDate());
        contestDTO.setEnrollmentStart(contest.getEnrollmentStart());
        contestDTO.setEnrollmentDue(contest.getEnrollmentDue());
        contestDTO.setPartners(contest.getPartners());
        User u = userRepository.findById(contest.getUserId());
        contestDTO.setUser(u);

        return contestDTO;
    }
}

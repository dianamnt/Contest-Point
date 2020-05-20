package com.contestpoint.mapper;

import com.contestpoint.dto.ParticipationContractDTO;
import com.contestpoint.dto.UserLikeDTO;
import com.contestpoint.model.Contest;
import com.contestpoint.model.ParticipationContract;
import com.contestpoint.model.User;
import com.contestpoint.model.UserLike;
import com.contestpoint.repository.ContestRepository;
import com.contestpoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipationContractMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContestRepository contestRepository;

    public ParticipationContractDTO toDTO(ParticipationContract pc) {
        ParticipationContractDTO pcDTO = new ParticipationContractDTO();

        pcDTO.setPcId(pc.getPcId());
        pcDTO.setContestId(pc.getContest().getContestId());
        pcDTO.setUserId(pc.getUser().getUserId());

        return pcDTO;
    }

    public ParticipationContract toEntity(ParticipationContractDTO pcDTO) {
        ParticipationContract pc = new ParticipationContract();

        pc.setPcId(pcDTO.getPcId());
        User u = userRepository.findById(pcDTO.getUserId());
        pc.setUser(u);
        Contest c = contestRepository.findById(pcDTO.getContestId());
        pc.setContest(c);

        return pc;
    }
}

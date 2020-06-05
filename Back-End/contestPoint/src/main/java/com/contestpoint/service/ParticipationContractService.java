package com.contestpoint.service;

import com.contestpoint.dto.ContractDetailedDTO;
import com.contestpoint.dto.ParticipationContractDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipationContractService {
    ParticipationContractDTO createParticipationContract(ParticipationContractDTO ParticipationContract) throws Exception;

    void deleteParticipationContract(Long ParticipationContractId);

    void updateParticipationContract(ParticipationContractDTO ParticipationContract) throws Exception;

    List<ParticipationContractDTO> findAllParticipationContracts();

    ParticipationContractDTO findById(Long id);

    ParticipationContractDTO isEnrolled(Long userId, Long contestId);

    List<ContractDetailedDTO> findAllDetailedContracts(Long contestId);
}

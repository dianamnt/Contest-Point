package com.contestpoint.service;

import com.contestpoint.dto.ParticipationContractDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipationContractService {
    void createParticipationContract(ParticipationContractDTO ParticipationContract) throws Exception;

    void deleteParticipationContract(Long ParticipationContractId);

    void updateParticipationContract(ParticipationContractDTO ParticipationContract) throws Exception;

    List<ParticipationContractDTO> findAllParticipationContracts();

    ParticipationContractDTO findById(Long id);
}

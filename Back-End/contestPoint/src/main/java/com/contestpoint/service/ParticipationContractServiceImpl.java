package com.contestpoint.service;

import com.contestpoint.dto.ParticipationContractDTO;
import com.contestpoint.mapper.ParticipationContractMapper;
import com.contestpoint.model.ParticipationContract;
import com.contestpoint.repository.ParticipationContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class ParticipationContractServiceImpl implements ParticipationContractService{
    @Autowired
    private com.contestpoint.repository.ParticipationContractRepository ParticipationContractRepository;

    @Autowired
    private com.contestpoint.mapper.ParticipationContractMapper ParticipationContractMapper;

    @Override
    @Transactional
    public void createParticipationContract(ParticipationContractDTO ParticipationContractDTO) {
        ParticipationContract ParticipationContract = ParticipationContractMapper.toEntity(ParticipationContractDTO);
        ParticipationContractRepository.saveData(ParticipationContract);
    }

    @Override
    @Transactional
    public void deleteParticipationContract(Long ParticipationContractId) {
        ParticipationContractRepository.removeData(ParticipationContractId);
    }

    @Override
    @Transactional
    public void updateParticipationContract(ParticipationContractDTO ParticipationContractDTO) {
        ParticipationContract ParticipationContract = ParticipationContractMapper.toEntity(ParticipationContractDTO);
        ParticipationContractRepository.updateData(ParticipationContract);
    }

    @Override
    @Transactional
    public List<ParticipationContractDTO> findAllParticipationContracts() {
        List<ParticipationContractDTO> ParticipationContractDTOList = new ArrayList<ParticipationContractDTO>();

        for (ParticipationContract ParticipationContract : ParticipationContractRepository.findAll()) {
            ParticipationContractDTO ParticipationContractDTO = ParticipationContractMapper.toDTO(ParticipationContract);
            ParticipationContractDTOList.add(ParticipationContractDTO);
        }

        return ParticipationContractDTOList;
    }

    @Override
    @Transactional
    public ParticipationContractDTO findById(Long id) {
        ParticipationContract ParticipationContract = ParticipationContractRepository.findById(id);
        if (ParticipationContract == null) {
            return null;
        }
        return ParticipationContractMapper.toDTO(ParticipationContract);
    }
}

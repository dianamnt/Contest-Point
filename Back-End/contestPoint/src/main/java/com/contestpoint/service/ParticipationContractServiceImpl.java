package com.contestpoint.service;

import com.contestpoint.dto.ContractDetailedDTO;
import com.contestpoint.dto.DetailDTO;
import com.contestpoint.dto.ParticipationContractDTO;
import com.contestpoint.mapper.ParticipationContractMapper;
import com.contestpoint.model.Contest;
import com.contestpoint.model.Detail;
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

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DetailService detailService;

    @Override
    @Transactional
    public ParticipationContractDTO createParticipationContract(ParticipationContractDTO participationContractDTO) {
        ParticipationContract participationContract = ParticipationContractMapper.toEntity(participationContractDTO);
        Long id = ParticipationContractRepository.saveData(participationContract);
        participationContract.setPcId(id);
        return ParticipationContractMapper.toDTO(participationContract);
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
    public List<ContractDetailedDTO> findAllDetailedContracts(Long contestId) {
        List<ParticipationContractDTO> participationContractDTOList = new ArrayList<>();
        List<ContractDetailedDTO> contractsDetailed = new ArrayList<>();

        for (ParticipationContract participationContract : ParticipationContractRepository.findAll()) {
            ParticipationContractDTO participationContractDTO = ParticipationContractMapper.toDTO(participationContract);
            participationContractDTOList.add(participationContractDTO);
        }

        for(ParticipationContractDTO pc: participationContractDTOList) {
            ContractDetailedDTO contractDetailedDTO = new ContractDetailedDTO();
            List<DetailDTO> details = new ArrayList<>();
            if(pc.getContestId() == contestId)
            {
                contractDetailedDTO.setPcId(pc.getPcId());
                contractDetailedDTO.setUserId(pc.getUserId());
                contractDetailedDTO.setContestId(pc.getContestId());
                contractDetailedDTO.setUserFirstName(userService.findById(pc.getUserId()).getFirstName());
                contractDetailedDTO.setUserLastName(userService.findById(pc.getUserId()).getLastName());
                contractDetailedDTO.setUserEmail(userService.findById(pc.getUserId()).getEmail());
                for(DetailDTO d: detailService.findAllDetails()) {
                    if(d.getPcId() == pc.getPcId()) {
                        details.add(d);
                    }
                }
                contractDetailedDTO.setDetails(details);
                contractsDetailed.add(contractDetailedDTO);
            }
        }

        return contractsDetailed;
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

package com.contestpoint.service;

import com.contestpoint.dto.RequirementDTO;
import com.contestpoint.mapper.RequirementMapper;
import com.contestpoint.model.Requirement;
import com.contestpoint.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class RequirementServiceImpl implements RequirementService{
    @Autowired
    private com.contestpoint.repository.RequirementRepository RequirementRepository;

    @Autowired
    private com.contestpoint.mapper.RequirementMapper RequirementMapper;

    @Override
    @Transactional
    public void createRequirement(RequirementDTO RequirementDTO) {
        Requirement Requirement = RequirementMapper.toEntity(RequirementDTO);
        RequirementRepository.saveData(Requirement);
    }

    @Override
    @Transactional
    public void deleteRequirement(Long RequirementId) {
        RequirementRepository.removeData(RequirementId);
    }

    @Override
    @Transactional
    public void updateRequirement(RequirementDTO RequirementDTO) {
        Requirement Requirement = RequirementMapper.toEntity(RequirementDTO);
        RequirementRepository.updateData(Requirement);
    }

    @Override
    @Transactional
    public List<RequirementDTO> findAllRequirements() {
        List<RequirementDTO> RequirementDTOList = new ArrayList<RequirementDTO>();

        for (Requirement Requirement : RequirementRepository.findAll()) {
            RequirementDTO RequirementDTO = RequirementMapper.toDTO(Requirement);
            RequirementDTOList.add(RequirementDTO);
        }

        return RequirementDTOList;
    }

    @Override
    @Transactional
    public RequirementDTO findById(Long id) {
        Requirement Requirement = RequirementRepository.findById(id);
        if (Requirement == null) {
            return null;
        }
        return RequirementMapper.toDTO(Requirement);
    }
}

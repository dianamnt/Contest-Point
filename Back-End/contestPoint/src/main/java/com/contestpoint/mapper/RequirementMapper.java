package com.contestpoint.mapper;

import com.contestpoint.dto.RequirementDTO;
import com.contestpoint.model.Contest;
import com.contestpoint.model.Requirement;
import com.contestpoint.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequirementMapper {

    @Autowired
    private ContestRepository contestRepository;

    public RequirementDTO toDTO(Requirement requirement) {
        RequirementDTO requirementDTO = new RequirementDTO();

        requirementDTO.setRequirementId(requirement.getRequirementId());
        requirementDTO.setOrderNo(requirement.getOrderNo());
        requirementDTO.setContent(requirement.getContent());
        requirementDTO.setIsMandatory(requirement.getIsMandatory());
        requirementDTO.setReqImage(requirement.getReqImage());
        requirementDTO.setContestId(requirement.getContest().getContestId());

        return requirementDTO;
    }

    public Requirement toEntity(RequirementDTO requirementDTO) {
        Requirement requirement = new Requirement();

        requirement.setRequirementId(requirementDTO.getRequirementId());
        requirement.setOrderNo(requirementDTO.getOrderNo());
        requirement.setContent(requirementDTO.getContent());
        requirement.setIsMandatory(requirementDTO.getIsMandatory());
        requirement.setReqImage(requirementDTO.getReqImage());
        Contest c = contestRepository.findById(requirementDTO.getContestId());
        requirement.setContest(c);

        return requirement;
    }
}

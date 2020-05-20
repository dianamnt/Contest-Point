package com.contestpoint.service;

import com.contestpoint.dto.RequirementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequirementService {
    void createRequirement(RequirementDTO Requirement) throws Exception;

    void deleteRequirement(Long RequirementId);

    void updateRequirement(RequirementDTO Requirement) throws Exception;

    List<RequirementDTO> findAllRequirements();

    RequirementDTO findById(Long id);
}

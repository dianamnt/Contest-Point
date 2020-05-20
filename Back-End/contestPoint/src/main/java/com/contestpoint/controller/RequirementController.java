package com.contestpoint.controller;

import com.contestpoint.dto.RequirementDTO;
import com.contestpoint.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    @GetMapping("/list")
    public ResponseEntity<List<RequirementDTO>> listRequirements() {
        return  ResponseEntity.ok(requirementService.findAllRequirements());
    }

    @PostMapping("/saveRequirement")
    public ResponseEntity<String> saveRequirement(@RequestBody RequirementDTO RequirementDTO) throws Exception {
        requirementService.createRequirement(RequirementDTO);
        return ResponseEntity.ok("Requirement saved");
    }

    @PostMapping("/deleteRequirement/{RequirementId}")
    public ResponseEntity<String> deleteRequirement(@PathVariable("RequirementId") Long id) {
        requirementService.deleteRequirement(id);
        return ResponseEntity.ok("Requirement deleted");
    }

    @PostMapping("/updateRequirement")
    public ResponseEntity<String> updateRequirement(@RequestBody RequirementDTO RequirementDTO) throws Exception {
        requirementService.updateRequirement(RequirementDTO);
        return ResponseEntity.ok("Requirement updated");
    }
}

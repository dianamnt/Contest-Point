package com.contestpoint.controller;

import com.contestpoint.dto.*;
import com.contestpoint.model.*;
import com.contestpoint.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationContestService locationContestService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagContestService tagContestService;

    @Autowired
    private RequirementService requirementService;

    @GetMapping("/list")
    public ResponseEntity<List<ContestDTO>> listContests() {
        return ResponseEntity.ok(contestService.findAllContests());
    }

    @GetMapping("/listDetailed")
    public ResponseEntity<List<ContestDetailedDTO>> listContestDetailed() {
        return ResponseEntity.ok(contestService.findAllContestDetailed());
    }

    @PostMapping("/saveContest")
    public ResponseEntity<?> saveContest(@RequestBody ContestDTO contestDTO) throws Exception {
        ContestDTO newcontestDTO = contestService.createContest(contestDTO);
        return new ResponseEntity<>(newcontestDTO, HttpStatus.OK);
    }

    @PostMapping("/saveContestDetailed")
    public ResponseEntity<?> saveContestDetailed(@RequestBody ContestDetailedDTO contestDetailedDTO) throws Exception {
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setUserId(contestDetailedDTO.getUserId());
        contestDTO.setContestId(contestDetailedDTO.getContestId());
        contestDTO.setContestName(contestDetailedDTO.getContestName());
        contestDTO.setDetails(contestDetailedDTO.getDetails());
        contestDTO.setCoverPicture(contestDetailedDTO.getCoverPicture());
        contestDTO.setPartners(contestDetailedDTO.getPartners());
        contestDTO.setStartDate(contestDetailedDTO.getStartDate());
        contestDTO.setEndDate(contestDetailedDTO.getEndDate());
        contestDTO.setEnrollmentStart(contestDetailedDTO.getEnrollmentStart());
        contestDTO.setEnrollmentDue(contestDetailedDTO.getEnrollmentDue());

        ContestDTO newcontestDTO = contestService.createContest(contestDTO);

        if(contestDetailedDTO.getLocations() != null) {
            LocationContestDTO locationContestDTO = new LocationContestDTO();
            locationContestDTO.setContestId(newcontestDTO.getContestId());
            for(LocationDTO l: contestDetailedDTO.getLocations())
            {
                LocationDTO newl = locationService.createLocation(l);
                locationContestDTO.setLocationId(newl.getLocationId());
                locationContestService.createLocationContest(locationContestDTO);
            }
        }

        if(contestDetailedDTO.getTags() != null) {
            TagContestDTO tagContestDTO = new TagContestDTO();
            tagContestDTO.setContestId(newcontestDTO.getContestId());
            for(TagDTO t: contestDetailedDTO.getTags())
            {
                if(tagService.findByName(t.getTagName()) == null) {
                    TagDTO newt = tagService.createTag(t);
                    tagContestDTO.setTagId(newt.getTagId());
                }
                tagContestDTO.setTagId(tagService.findByName(t.getTagName()).getTagId());
                tagContestService.createTagContest(tagContestDTO);
            }
        }

        if(contestDetailedDTO.getRequirements() != null) {
            for(RequirementDTO r: contestDetailedDTO.getRequirements())
            {
                r.setContestId(newcontestDTO.getContestId());
                RequirementDTO newr = requirementService.createRequirement(r);
            }
        }

        return new ResponseEntity<>(newcontestDTO, HttpStatus.OK);

    }

    @PostMapping("/deleteContest/{ContestId}")
    public ResponseEntity<String> deleteContest(@PathVariable("ContestId") Long id) {
        contestService.deleteContest(id);
        return ResponseEntity.ok("Contest deleted");
    }

    @PostMapping("/updateContest")
    public ResponseEntity<String> updateContest(@RequestBody ContestDTO ContestDTO) throws Exception {
        contestService.updateContest(ContestDTO);
        return ResponseEntity.ok("Contest updated");
    }
}

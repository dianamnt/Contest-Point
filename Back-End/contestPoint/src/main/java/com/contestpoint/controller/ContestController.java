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

    @GetMapping("/listMyContests")
    public ResponseEntity<List<ContestDetailedDTO>> listMyContests(@RequestParam Long userId) {
        return ResponseEntity.ok(contestService.findAllMyContests(userId));
    }

    @GetMapping("/listEnrolledContests")
    public ResponseEntity<List<ContestDetailedDTO>> listEnrolledContests(@RequestParam Long userId) {
        return ResponseEntity.ok(contestService.findAllContestEnrolled(userId));
    }

    @GetMapping("/listEnrolledContestsUpcoming")
    public ResponseEntity<List<ContestDetailedDTO>> listEnrolledContestsUpcoming(@RequestParam Long userId) {
        return ResponseEntity.ok(contestService.findAllContestsEnrolledUpcoming(userId));
    }

    @GetMapping("/listLikedContests")
    public ResponseEntity<List<ContestDetailedDTO>> listLikedContests(@RequestParam Long userId) {
        return ResponseEntity.ok(contestService.findAllContestsLiked(userId));
    }

    @GetMapping("/listLikedContestsUpcoming")
    public ResponseEntity<List<ContestDetailedDTO>> listLikedContestsUpcoming(@RequestParam Long userId) {
        return ResponseEntity.ok(contestService.findAllContestsLikedUpcoming(userId));
    }

    @GetMapping("/filterByTag")
    public ResponseEntity<List<ContestDetailedDTO>> filterByTag(@RequestParam String name) {
        return ResponseEntity.ok(contestService.filterByTag(name));
    }

    @GetMapping("/trendingContests")
    public ResponseEntity<List<ContestDetailedDTO>> trandingContests() {
        return ResponseEntity.ok(contestService.trendingContests());
    }

    @PostMapping("/saveContest")
    public ResponseEntity<?> saveContest(@RequestBody ContestDTO contestDTO) throws Exception {
        ContestDTO newcontestDTO = contestService.createContest(contestDTO);
        return new ResponseEntity<>(newcontestDTO, HttpStatus.OK);
    }

    @PostMapping("/findByIdDetailed")
    public ResponseEntity<?> findByIdDetailed(@RequestBody Long id) {
        ContestDetailedDTO newcontestDTO = contestService.findByIdDetailed(id);
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

    @PostMapping("/deleteContest")
    public ResponseEntity<?> deleteContest(@RequestBody AuxDTO auxDTO) {
        if(contestService.findById(auxDTO.getSecondSensitiveDataParam()).getUserId() == auxDTO.getFirstSensitiveDataParam()) {
            contestService.deleteContest(auxDTO.getSecondSensitiveDataParam());
            return new ResponseEntity<>(auxDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/updateContest")
    public ResponseEntity<?> updateContest(@RequestBody ContestDTO contestDTO) throws Exception {
        contestService.updateContest(contestDTO);
        return new ResponseEntity<>(contestDTO, HttpStatus.OK);
    }
}

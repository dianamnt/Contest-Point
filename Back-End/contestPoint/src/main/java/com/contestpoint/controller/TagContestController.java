package com.contestpoint.controller;

import com.contestpoint.dto.TagContestDTO;
import com.contestpoint.service.TagContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/TagContestContest")
public class TagContestController {
    @Autowired
    private TagContestService tagContestService;

    @GetMapping("/list")
    public ResponseEntity<List<TagContestDTO>> listTagContests() {
        return  ResponseEntity.ok(tagContestService.findAllTagContests());
    }

    @PostMapping("/saveTagContest")
    public ResponseEntity<String> saveTagContest(@RequestBody TagContestDTO TagContestDTO) throws Exception {
        tagContestService.createTagContest(TagContestDTO);
        return ResponseEntity.ok("TagContest saved");
    }

    @PostMapping("/deleteTagContest/{TagContestId}")
    public ResponseEntity<String> deleteTagContest(@PathVariable("TagContestId") Long id) {
        tagContestService.deleteTagContest(id);
        return ResponseEntity.ok("TagContest deleted");
    }

    @PostMapping("/updateTagContest")
    public ResponseEntity<String> updateTagContest(@RequestBody TagContestDTO TagContestDTO) throws Exception {
        tagContestService.updateTagContest(TagContestDTO);
        return ResponseEntity.ok("TagContest updated");
    }
}

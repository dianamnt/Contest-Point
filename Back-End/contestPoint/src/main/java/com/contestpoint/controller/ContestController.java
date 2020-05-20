package com.contestpoint.controller;

import com.contestpoint.dto.ContestDTO;
import com.contestpoint.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @GetMapping("/list")
    public ResponseEntity<List<ContestDTO>> listContests() {
        return  ResponseEntity.ok(contestService.findAllContests());
    }

    @PostMapping("/saveContest")
    public ResponseEntity<String> saveContest(@RequestBody ContestDTO ContestDTO) throws Exception {
        contestService.createContest(ContestDTO);
        return ResponseEntity.ok("Contest saved");
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

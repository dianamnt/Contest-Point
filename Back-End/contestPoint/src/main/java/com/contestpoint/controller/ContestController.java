package com.contestpoint.controller;

import com.contestpoint.dto.ContestDTO;
import com.contestpoint.dto.UserDTO;
import com.contestpoint.model.Contest;
import com.contestpoint.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> saveContest(@RequestBody ContestDTO contestDTO) throws Exception {
        if(contestService.findByEverything(contestDTO.getContestName(), contestDTO.getUserId()) == null)
        {
            contestService.createContest(contestDTO);
            return new ResponseEntity<>(contestDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

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

    @PostMapping("/findByEverything")
    public ResponseEntity<?> findByEmail(@RequestBody ContestDTO contestDTO) throws Exception {
        ContestDTO aux = contestService.findByEverything(contestDTO.getContestName(), contestDTO.getUserId());
        if(aux != null)
        {
            return new ResponseEntity<>(aux, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

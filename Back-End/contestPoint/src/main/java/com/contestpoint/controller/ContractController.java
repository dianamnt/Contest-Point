package com.contestpoint.controller;

import com.contestpoint.dto.ParticipationContractDTO;
import com.contestpoint.service.ParticipationContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ParticipationContractService participationContractService;

    @GetMapping("/list")
    public ResponseEntity<List<ParticipationContractDTO>> listParticipationContracts() {
        return  ResponseEntity.ok(participationContractService.findAllParticipationContracts());
    }

    @PostMapping("/saveParticipationContract")
    public ResponseEntity<String> saveParticipationContract(@RequestBody ParticipationContractDTO ParticipationContractDTO) throws Exception {
        participationContractService.createParticipationContract(ParticipationContractDTO);
        return ResponseEntity.ok("ParticipationContract saved");
    }

    @PostMapping("/deleteParticipationContract/{ParticipationContractId}")
    public ResponseEntity<String> deleteParticipationContract(@PathVariable("ParticipationContractId") Long id) {
        participationContractService.deleteParticipationContract(id);
        return ResponseEntity.ok("ParticipationContract deleted");
    }

    @PostMapping("/updateParticipationContract")
    public ResponseEntity<String> updateParticipationContract(@RequestBody ParticipationContractDTO ParticipationContractDTO) throws Exception {
        participationContractService.updateParticipationContract(ParticipationContractDTO);
        return ResponseEntity.ok("ParticipationContract updated");
    }
}

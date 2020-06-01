package com.contestpoint.controller;

import com.contestpoint.dto.ContractDetailedDTO;
import com.contestpoint.dto.DetailDTO;
import com.contestpoint.dto.ParticipationContractDTO;
import com.contestpoint.service.ContestService;
import com.contestpoint.service.DetailService;
import com.contestpoint.service.ParticipationContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ParticipationContractService participationContractService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private DetailService detailService;

    @GetMapping("/list")
    public ResponseEntity<List<ParticipationContractDTO>> listParticipationContracts() {
        return  ResponseEntity.ok(participationContractService.findAllParticipationContracts());
    }

    @GetMapping("/listDetailed/{userId}/{contestId}")
    public ResponseEntity<List<ContractDetailedDTO>> listParticipationContracts(@PathVariable("userId") Long userId, @PathVariable("contestId") Long contestId) {
        if(contestService.findById(contestId).getUserId() == userId)
            return new ResponseEntity<>(participationContractService.findAllDetailedContracts(userId, contestId), HttpStatus.OK);
        List<ContractDetailedDTO> nullList = new ArrayList<>();
        return new ResponseEntity<>(nullList, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> savecontractDetailed(@RequestBody ContractDetailedDTO contractDetailedDTO) throws Exception {
        ParticipationContractDTO participationContractDTO = new ParticipationContractDTO();
        participationContractDTO.setContestId(contractDetailedDTO.getContestId());
        participationContractDTO.setUserId(contractDetailedDTO.getUserId());
        ParticipationContractDTO newParticipationContractDTO = participationContractService.createParticipationContract(participationContractDTO);
        if(contractDetailedDTO.getDetails() != null)
        {
            for(DetailDTO d: contractDetailedDTO.getDetails()) {
                d.setPcId(newParticipationContractDTO.getPcId());
                detailService.createDetail(d);
            }
        }
        return new ResponseEntity<>(newParticipationContractDTO, HttpStatus.OK);
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

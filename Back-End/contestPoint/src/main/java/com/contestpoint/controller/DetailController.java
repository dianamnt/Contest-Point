package com.contestpoint.controller;

import com.contestpoint.dto.DetailDTO;
import com.contestpoint.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/list")
    public ResponseEntity<List<DetailDTO>> listDetails() {
        return  ResponseEntity.ok(detailService.findAllDetails());
    }

    @PostMapping("/saveDetail")
    public ResponseEntity<String> saveDetail(@RequestBody DetailDTO DetailDTO) throws Exception {
        detailService.createDetail(DetailDTO);
        return ResponseEntity.ok("Detail saved");
    }

    @PostMapping("/deleteDetail/{DetailId}")
    public ResponseEntity<String> deleteDetail(@PathVariable("DetailId") Long id) {
        detailService.deleteDetail(id);
        return ResponseEntity.ok("Detail deleted");
    }

    @PostMapping("/updateDetail")
    public ResponseEntity<String> updateDetail(@RequestBody DetailDTO DetailDTO) throws Exception {
        detailService.updateDetail(DetailDTO);
        return ResponseEntity.ok("Detail updated");
    }
}

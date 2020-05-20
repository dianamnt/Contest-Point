package com.contestpoint.controller;

import com.contestpoint.dto.LocationContestDTO;
import com.contestpoint.service.LocationContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/locationContest")
public class LocationContestController {
    @Autowired
    private LocationContestService locationContestService;

    @GetMapping("/list")
    public ResponseEntity<List<LocationContestDTO>> listLocationContests() {
        return  ResponseEntity.ok(locationContestService.findAllLocationContests());
    }

    @PostMapping("/saveLocationContest")
    public ResponseEntity<String> saveLocationContest(@RequestBody LocationContestDTO LocationContestDTO) throws Exception {
        locationContestService.createLocationContest(LocationContestDTO);
        return ResponseEntity.ok("LocationContest saved");
    }

    @PostMapping("/deleteLocationContest/{LocationContestId}")
    public ResponseEntity<String> deleteLocationContest(@PathVariable("LocationContestId") Long id) {
        locationContestService.deleteLocationContest(id);
        return ResponseEntity.ok("LocationContest deleted");
    }

    @PostMapping("/updateLocationContest")
    public ResponseEntity<String> updateLocationContest(@RequestBody LocationContestDTO LocationContestDTO) throws Exception {
        locationContestService.updateLocationContest(LocationContestDTO);
        return ResponseEntity.ok("LocationContest updated");
    }
}

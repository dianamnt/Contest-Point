package com.contestpoint.controller;

import com.contestpoint.dto.LocationDTO;
import com.contestpoint.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/list")
    public ResponseEntity<List<LocationDTO>> listLocations() {
        return  ResponseEntity.ok(locationService.findAllLocations());
    }

    @PostMapping("/saveLocation")
    public ResponseEntity<String> saveLocation(@RequestBody LocationDTO LocationDTO) throws Exception {
        locationService.createLocation(LocationDTO);
        return ResponseEntity.ok("Location saved");
    }

    @PostMapping("/deleteLocation/{LocationId}")
    public ResponseEntity<String> deleteLocation(@PathVariable("LocationId") Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Location deleted");
    }

    @PostMapping("/updateLocation")
    public ResponseEntity<String> updateLocation(@RequestBody LocationDTO LocationDTO) throws Exception {
        locationService.updateLocation(LocationDTO);
        return ResponseEntity.ok("Location updated");
    }
}

package com.contestpoint.mapper;

import com.contestpoint.dto.LocationContestDTO;
import com.contestpoint.dto.TagContestDTO;
import com.contestpoint.model.*;
import com.contestpoint.repository.ContestRepository;
import com.contestpoint.repository.LocationRepository;
import com.contestpoint.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationContestMapper {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ContestRepository contestRepository;

    public LocationContestDTO toDTO(LocationContest locationContest) {
        LocationContestDTO locationContestDTO = new LocationContestDTO();

        locationContestDTO.setLcId(locationContest.getLcId());
        locationContestDTO.setLocationId(locationContest.getLocation().getLocationId());
        locationContestDTO.setContestId(locationContest.getContest().getContestId());

        return locationContestDTO;
    }

    public LocationContest toEntity(LocationContestDTO locationContestDTO) {
        LocationContest locationContest = new LocationContest();

        locationContest.setLcId(locationContestDTO.getLcId());
        Location l = locationRepository.findById(locationContestDTO.getLocationId());
        locationContest.setLocation(l);
        Contest c = contestRepository.findById(locationContestDTO.getContestId());
        locationContest.setContest(c);

        return locationContest;
    }
}

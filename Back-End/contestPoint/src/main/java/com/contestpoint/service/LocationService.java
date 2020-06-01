package com.contestpoint.service;

import com.contestpoint.dto.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    LocationDTO createLocation(LocationDTO Location) throws Exception;

    void deleteLocation(Long LocationId);

    void updateLocation(LocationDTO Location) throws Exception;

    List<LocationDTO> findAllLocations();

    LocationDTO findById(Long id);
}

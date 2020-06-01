package com.contestpoint.service;

import com.contestpoint.dto.LocationDTO;
import com.contestpoint.mapper.LocationMapper;
import com.contestpoint.model.Location;
import com.contestpoint.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class LocationServiceImpl implements LocationService{
    @Autowired
    private com.contestpoint.repository.LocationRepository LocationRepository;

    @Autowired
    private com.contestpoint.mapper.LocationMapper LocationMapper;

    @Override
    @Transactional
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = LocationMapper.toEntity(locationDTO);
        Long id = LocationRepository.saveData(location);
        location.setLocationId(id);
        return LocationMapper.toDTO(location);
    }

    @Override
    @Transactional
    public void deleteLocation(Long LocationId) {
        LocationRepository.removeData(LocationId);
    }

    @Override
    @Transactional
    public void updateLocation(LocationDTO LocationDTO) {
        Location Location = LocationMapper.toEntity(LocationDTO);
        LocationRepository.updateData(Location);
    }

    @Override
    @Transactional
    public List<LocationDTO> findAllLocations() {
        List<LocationDTO> LocationDTOList = new ArrayList<LocationDTO>();

        for (Location Location : LocationRepository.findAll()) {
            LocationDTO LocationDTO = LocationMapper.toDTO(Location);
            LocationDTOList.add(LocationDTO);
        }

        return LocationDTOList;
    }

    @Override
    @Transactional
    public LocationDTO findById(Long id) {
        Location Location = LocationRepository.findById(id);
        if (Location == null) {
            return null;
        }
        return LocationMapper.toDTO(Location);
    }
}

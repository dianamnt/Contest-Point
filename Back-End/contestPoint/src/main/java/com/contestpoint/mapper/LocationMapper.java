package com.contestpoint.mapper;

import com.contestpoint.dto.LocationDTO;
import com.contestpoint.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setBuildingNumber(location.getBuildingNumber());
        locationDTO.setCity(location.getCity());
        locationDTO.setStreetName(location.getStreetName());
        locationDTO.setStreetNumber(location.getStreetNumber());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setRegion(location.getRegion());
        locationDTO.setPostalCode(location.getPostalCode());
        locationDTO.setOnlineResource(location.getOnlineResource());
        locationDTO.setDetails(location.getDetails());

        return locationDTO;
    }

    public Location toEntity(LocationDTO location) {
        Location locationDTO = new Location();

        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setBuildingNumber(location.getBuildingNumber());
        locationDTO.setCity(location.getCity());
        locationDTO.setStreetName(location.getStreetName());
        locationDTO.setStreetNumber(location.getStreetNumber());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setRegion(location.getRegion());
        locationDTO.setPostalCode(location.getPostalCode());
        locationDTO.setOnlineResource(location.getOnlineResource());
        locationDTO.setDetails(location.getDetails());

        return locationDTO;
    }
}

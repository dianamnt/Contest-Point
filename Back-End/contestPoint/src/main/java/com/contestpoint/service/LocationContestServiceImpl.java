package com.contestpoint.service;

import com.contestpoint.dto.LocationContestDTO;
import com.contestpoint.mapper.LocationContestMapper;
import com.contestpoint.model.LocationContest;
import com.contestpoint.repository.LocationContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class LocationContestServiceImpl implements LocationContestService{
    @Autowired
    private com.contestpoint.repository.LocationContestRepository LocationContestRepository;

    @Autowired
    private com.contestpoint.mapper.LocationContestMapper LocationContestMapper;

    @Override
    @Transactional
    public void createLocationContest(LocationContestDTO LocationContestDTO) {
        LocationContest LocationContest = LocationContestMapper.toEntity(LocationContestDTO);
        LocationContestRepository.saveData(LocationContest);
    }

    @Override
    @Transactional
    public void deleteLocationContest(Long LocationContestId) {
        LocationContestRepository.removeData(LocationContestId);
    }

    @Override
    @Transactional
    public void updateLocationContest(LocationContestDTO LocationContestDTO) {
        LocationContest LocationContest = LocationContestMapper.toEntity(LocationContestDTO);
        LocationContestRepository.updateData(LocationContest);
    }

    @Override
    @Transactional
    public List<LocationContestDTO> findAllLocationContests() {
        List<LocationContestDTO> LocationContestDTOList = new ArrayList<LocationContestDTO>();

        for (LocationContest LocationContest : LocationContestRepository.findAll()) {
            LocationContestDTO LocationContestDTO = LocationContestMapper.toDTO(LocationContest);
            LocationContestDTOList.add(LocationContestDTO);
        }

        return LocationContestDTOList;
    }

    @Override
    @Transactional
    public LocationContestDTO findById(Long id) {
        LocationContest LocationContest = LocationContestRepository.findById(id);
        if (LocationContest == null) {
            return null;
        }
        return LocationContestMapper.toDTO(LocationContest);
    }
}

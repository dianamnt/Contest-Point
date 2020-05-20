package com.contestpoint.service;

import com.contestpoint.dto.LocationContestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationContestService {
    void createLocationContest(LocationContestDTO LocationContest) throws Exception;

    void deleteLocationContest(Long LocationContestId);

    void updateLocationContest(LocationContestDTO LocationContest) throws Exception;

    List<LocationContestDTO> findAllLocationContests();

    LocationContestDTO findById(Long id);
}

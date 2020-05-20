package com.contestpoint.service;

import com.contestpoint.dto.DetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailService {
    void createDetail(DetailDTO Detail) throws Exception;

    void deleteDetail(Long DetailId);

    void updateDetail(DetailDTO Detail) throws Exception;

    List<DetailDTO> findAllDetails();

    DetailDTO findById(Long id);
}

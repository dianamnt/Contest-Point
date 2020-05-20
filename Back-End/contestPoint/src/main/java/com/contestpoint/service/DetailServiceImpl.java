package com.contestpoint.service;

import com.contestpoint.dto.DetailDTO;
import com.contestpoint.mapper.DetailMapper;
import com.contestpoint.model.Detail;
import com.contestpoint.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class DetailServiceImpl implements DetailService {
    @Autowired
    private com.contestpoint.repository.DetailRepository DetailRepository;

    @Autowired
    private com.contestpoint.mapper.DetailMapper DetailMapper;

    @Override
    @Transactional
    public void createDetail(DetailDTO DetailDTO) {
        Detail Detail = DetailMapper.toEntity(DetailDTO);
        DetailRepository.saveData(Detail);
    }

    @Override
    @Transactional
    public void deleteDetail(Long DetailId) {
        DetailRepository.removeData(DetailId);
    }

    @Override
    @Transactional
    public void updateDetail(DetailDTO DetailDTO) {
        Detail Detail = DetailMapper.toEntity(DetailDTO);
        DetailRepository.updateData(Detail);
    }

    @Override
    @Transactional
    public List<DetailDTO> findAllDetails() {
        List<DetailDTO> DetailDTOList = new ArrayList<DetailDTO>();

        for (Detail Detail : DetailRepository.findAll()) {
            DetailDTO DetailDTO = DetailMapper.toDTO(Detail);
            DetailDTOList.add(DetailDTO);
        }

        return DetailDTOList;
    }

    @Override
    @Transactional
    public DetailDTO findById(Long id) {
        Detail Detail = DetailRepository.findById(id);
        if (Detail == null) {
            return null;
        }
        return DetailMapper.toDTO(Detail);
    }
}

package com.contestpoint.mapper;

import com.contestpoint.dto.DetailDTO;
import com.contestpoint.model.Detail;
import com.contestpoint.model.ParticipationContract;
import com.contestpoint.repository.ParticipationContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailMapper {

    @Autowired
    private ParticipationContractRepository participationContractRepository;

    public DetailDTO toDTO(Detail detail) {
        DetailDTO detailDTO = new DetailDTO();

        detailDTO.setDetailId(detail.getDetailId());
        detailDTO.setOrderNo(detail.getOrderNo());
        detailDTO.setTextContent(detail.getTextContent());
        detailDTO.setImageContent(detail.getImageContent());
        detailDTO.setPcId(detail.getContract().getPcId());

        return detailDTO;
    }

    public Detail toEntity(DetailDTO detailDTO) {
        Detail detail = new Detail();

        detail.setDetailId(detailDTO.getDetailId());
        detail.setOrderNo(detailDTO.getOrderNo());
        detail.setTextContent(detailDTO.getTextContent());
        detail.setImageContent(detailDTO.getImageContent());
        ParticipationContract pc = participationContractRepository.findById(detailDTO.getPcId());
        detail.setContract(pc);

        return detail;
    }
}

package com.contestpoint.service;

import com.contestpoint.dto.TagDTO;
import com.contestpoint.mapper.TagMapper;
import com.contestpoint.model.Tag;
import com.contestpoint.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class TagServiceImpl implements TagService{
    @Autowired
    private com.contestpoint.repository.TagRepository TagRepository;

    @Autowired
    private com.contestpoint.mapper.TagMapper TagMapper;

    @Override
    @Transactional
    public void createTag(TagDTO TagDTO) {
        Tag Tag = TagMapper.toEntity(TagDTO);
        TagRepository.saveData(Tag);
    }

    @Override
    @Transactional
    public void deleteTag(Long TagId) {
        TagRepository.removeData(TagId);
    }

    @Override
    @Transactional
    public void updateTag(TagDTO TagDTO) {
        Tag Tag = TagMapper.toEntity(TagDTO);
        TagRepository.updateData(Tag);
    }

    @Override
    @Transactional
    public List<TagDTO> findAllTags() {
        List<TagDTO> TagDTOList = new ArrayList<TagDTO>();

        for (Tag Tag : TagRepository.findAll()) {
            TagDTO TagDTO = TagMapper.toDTO(Tag);
            TagDTOList.add(TagDTO);
        }

        return TagDTOList;
    }

    @Override
    @Transactional
    public TagDTO findById(Long id) {
        Tag Tag = TagRepository.findById(id);
        if (Tag == null) {
            return null;
        }
        return TagMapper.toDTO(Tag);
    }
}

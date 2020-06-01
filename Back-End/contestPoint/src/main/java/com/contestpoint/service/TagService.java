package com.contestpoint.service;

import com.contestpoint.dto.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    TagDTO createTag(TagDTO Tag) throws Exception;

    void deleteTag(Long TagId);

    void updateTag(TagDTO Tag) throws Exception;

    List<TagDTO> findAllTags();

    TagDTO findById(Long id);

    TagDTO findByName(String name);
}

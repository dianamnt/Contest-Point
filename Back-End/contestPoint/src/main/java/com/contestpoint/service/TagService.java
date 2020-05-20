package com.contestpoint.service;

import com.contestpoint.dto.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    void createTag(TagDTO Tag) throws Exception;

    void deleteTag(Long TagId);

    void updateTag(TagDTO Tag) throws Exception;

    List<TagDTO> findAllTags();

    TagDTO findById(Long id);
}

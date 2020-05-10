package com.contestpoint.mapper;

import com.contestpoint.dto.TagDTO;
import com.contestpoint.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();

        tagDTO.setTagId(tag.getTagId());
        tagDTO.setTagName(tag.getTagName());

        return tagDTO;
    }

    public Tag toEntity(TagDTO tagDTO) {
        Tag tag = new Tag();

        tag.setTagId(tagDTO.getTagId());
        tag.setTagName(tagDTO.getTagName());

        return tag;
    }
}

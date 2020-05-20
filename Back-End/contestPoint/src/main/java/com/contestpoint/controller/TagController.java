package com.contestpoint.controller;

import com.contestpoint.dto.TagDTO;
import com.contestpoint.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseEntity<List<TagDTO>> listTags() {
        return  ResponseEntity.ok(tagService.findAllTags());
    }

    @PostMapping("/saveTag")
    public ResponseEntity<String> saveTag(@RequestBody TagDTO tagDTO) throws Exception {
        tagService.createTag(tagDTO);
        return ResponseEntity.ok("Tag saved");
    }

    @PostMapping("/deleteTag/{TagId}")
    public ResponseEntity<String> deleteTag(@PathVariable("TagId") Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("Tag deleted");
    }

    @PostMapping("/updateTag")
    public ResponseEntity<String> updateTag(@RequestBody TagDTO tagDTO) throws Exception {
        tagService.updateTag(tagDTO);
        return ResponseEntity.ok("Tag updated");
    }
}

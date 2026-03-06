package org.example.quoraappcloneapplication.controllers;

import org.example.quoraappcloneapplication.Services.TagService;
import org.example.quoraappcloneapplication.dtos.TagDTO;
import org.example.quoraappcloneapplication.modles.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping
    public List<Tag> getAllTag(){
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){

        Optional<Tag> tag = tagService.getTagById(id);

        return tag.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }
    @PostMapping
    public Tag createTag(@RequestBody TagDTO tagDTO){
        return tagService.createTag(tagDTO);
    }
}

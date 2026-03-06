package org.example.quoraappcloneapplication.Services;

import org.example.quoraappcloneapplication.dtos.TagDTO;
import org.example.quoraappcloneapplication.modles.Tag;
import org.example.quoraappcloneapplication.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;
    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags(){

        return tagRepository.findAll();
    }

    public void deleteTagById(Long id){

        tagRepository.deleteById(id);
    }

    public Tag createTag(TagDTO tagDTO){
        Tag tag = new Tag();

        tag.setName(tagDTO.getName());
        return tagRepository.save(tag);
    }

    public Optional<Tag> getTagById(Long id){

        return tagRepository.findById(id);
    }

}

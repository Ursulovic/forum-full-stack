package com.example.raf_backend.services;

import com.example.raf_backend.entities.Tag;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.repositories.tag.TagRepository;
import com.example.raf_backend.resources.Secured;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Secured({UserType.CONTENT_CREATOR})
public class TagService {

    @Inject
    private TagRepository tagRepository;

    public boolean addTag( Tag tag) {
        return this.tagRepository.addTag(tag);
    }

    public List<String> fetchTags() {
        return this.tagRepository.fetchTags();
    }




}

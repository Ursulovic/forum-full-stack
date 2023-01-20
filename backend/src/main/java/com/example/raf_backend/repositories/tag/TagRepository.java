package com.example.raf_backend.repositories.tag;

import com.example.raf_backend.entities.Tag;

import java.util.List;

public interface TagRepository {
    public boolean addTag(Tag tag);
    public List<String> fetchTags();
}

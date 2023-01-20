package com.example.raf_backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    @NotNull(message = "id field required")
    @NotEmpty(message = "id field required")
    private Integer id;

    @NotNull(message = "tags field required")
    @NotEmpty(message = "tags field required")
    private String name;

    public Tag() {
    }

    public Tag(Integer id, String tags) {
        this.id = id;
        this.name = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTags() {
        return name;
    }

    public void setTags(String tags) {
        this.name = tags;
    }
}

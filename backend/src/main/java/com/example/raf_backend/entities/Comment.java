package com.example.raf_backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Comment {
    @NotNull(message = "id field required")
    @NotEmpty(message = "id field required")
    private Integer id;

    @NotNull(message = "text field required")
    @NotEmpty(message = "text field required")
    private String text;

    @NotNull(message = "author field required")
    @NotEmpty(message = "author field required")
    private String author;

    @NotNull(message = "date field required")
    @NotEmpty(message = "date field required")
    private Long date;

    public Comment() {
    }

    public Comment(Integer id, String text, String author, Long date) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }



    public void setAuthor(String author) {
        this.author = author;
    }


}

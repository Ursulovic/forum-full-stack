package com.example.raf_backend.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditArticleRequest {

    @NotNull(message = "Id field required")
    @NotEmpty(message = "Id field required")
    private Integer id;

    @NotNull(message = "Category field required")
    @NotEmpty(message = "Category field required")
    private String category;

    @NotNull(message = "Title field required")
    @NotEmpty(message = "Title field required")
    private String title;

    @NotNull(message = "Text field required")
    @NotEmpty(message = "Text field required")
    private String text;


    @NotNull(message = "Visits field required")
    @NotEmpty(message = "Visits field required")
    private Integer visits;

    @NotNull(message = "Author field required")
    @NotEmpty(message = "Author field required")
    private String author;

    public EditArticleRequest() {
    }


    public EditArticleRequest(Integer id, String category, String title, String text, Integer visits, String author) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.text = text;
        this.visits = visits;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

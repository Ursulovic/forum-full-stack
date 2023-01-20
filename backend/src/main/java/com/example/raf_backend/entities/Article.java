package com.example.raf_backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Article {

    @NotNull(message = "id field required")
    @NotEmpty(message = "id field required")
    private Integer id;
    @NotNull(message = "category field required")
    @NotEmpty(message = "category field required")
    private String category;

    @NotNull(message = "title field required")
    @NotEmpty(message = "title field required")
    private String title;

    @NotNull(message = "text field required")
    @NotEmpty(message = "text field required")
    private String text;

    @NotNull(message = "date field required")
    @NotEmpty(message = "date field required")
    private Long date;

    @NotNull(message = "visits field required")
    @NotEmpty(message = "visits field required")
    private Integer visits;

    @NotNull(message = "author field required")
    @NotEmpty(message = "author field required")
    private String author;

    @NotNull(message = "author field required")
    @NotEmpty(message = "author field required")
    private String tags;


    public Article() { }


    public Article(Integer id, String category, String title, String text, Long date, Integer visits, String author, String tags) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.text = text;
        this.date = date;
        this.visits = visits;
        this.author = author;
        this.tags = tags;
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", visits=" + visits +
                ", author='" + author + '\'' +
                '}';
    }
}

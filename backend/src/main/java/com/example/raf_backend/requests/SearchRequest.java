package com.example.raf_backend.requests;

public class SearchRequest {
    private String text;

    public SearchRequest(String text) {
        this.text = text;
    }

    public SearchRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

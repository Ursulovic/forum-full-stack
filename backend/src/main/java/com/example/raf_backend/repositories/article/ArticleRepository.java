package com.example.raf_backend.repositories.article;

import com.example.raf_backend.entities.Article;
import com.example.raf_backend.requests.EditArticleRequest;

import java.util.List;

public interface ArticleRepository {
    public boolean addArticle(Article article);
    public List<Article> fetchArticles(int page);
    public List<Article> fetchAllArticles();
    public List<Article> categoryFilterArticles(String category);
    public List<Article> fetchMostReaded();
    public List<Article> tagFilterArticles(Integer id);
    public List<Article> searchByText(String text);
    public Article fetchSingleArticle(int id);
    public boolean editArticle (EditArticleRequest request);
    public int getTotal();
    public void removeArticle(int id);
    public void increaseVisit(int id);

}

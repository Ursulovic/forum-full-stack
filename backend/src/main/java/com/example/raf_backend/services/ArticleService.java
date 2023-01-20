package com.example.raf_backend.services;

import com.example.raf_backend.entities.Article;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.repositories.article.ArticleRepository;
import com.example.raf_backend.requests.EditArticleRequest;
import com.example.raf_backend.resources.Secured;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
@Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    public boolean addArticle( Article article) {
        System.out.println();
        return this.articleRepository.addArticle(article);
    }

    public List<Article> fetchArticles(int page) {
        return this.articleRepository.fetchArticles(page);
    }

    public List<Article> fetchAllArticles() {return this.articleRepository.fetchAllArticles();}

    public List<Article> filterByCategory(String category) {
        return this.articleRepository.categoryFilterArticles(category);
    }

    public Article fetchSingleArticle(int id) { return this.articleRepository.fetchSingleArticle(id);}

    public List<Article> filterByTag(Integer id) {
        return this.articleRepository.tagFilterArticles(id);
    }

    public List<Article> searchByText(String text) {return this.articleRepository.searchByText(text);}

    public List<Article> getMostReaded() {return this.articleRepository.fetchMostReaded();}

    public boolean editArticle ( EditArticleRequest request) {
        return this.articleRepository.editArticle(request);
    }

    public int getTotal() {return this.articleRepository.getTotal();}

    public void removeArticle(int id) {this.articleRepository.removeArticle(id);}

    public void increaseVisits(int id) {this.articleRepository.increaseVisit(id);}





}

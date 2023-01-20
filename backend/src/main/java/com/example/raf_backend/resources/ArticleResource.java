package com.example.raf_backend.resources;

import com.example.raf_backend.entities.Article;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.requests.EditArticleRequest;
import com.example.raf_backend.requests.SearchRequest;
import com.example.raf_backend.services.ArticleService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/article")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addArticle(@Valid Article article) {

        if (this.articleService.addArticle(article)) {
            System.out.println("uspesno!");
            return Response.ok().build();
        }
        else
            return Response.status(409).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch/mostReaded")
    public List<Article> getMostReaded() {
        return this.articleService.getMostReaded();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch/{page}")
    public List<Article> fetchArticles(@PathParam("page") int page) {
        return this.articleService.fetchArticles(page);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch")
    public List<Article> fetchAllArticles() {return this.articleService.fetchAllArticles();}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch/single/{id}")
    public Article fetchSingleArticle(@PathParam("id") int id) {
        return this.articleService.fetchSingleArticle(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categoryFilter/{category}")
    public List<Article> filterByCategory(@PathParam("category") String catgory) {
        return this.articleService.filterByCategory(catgory);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tagFilter/{id}")
    public List<Article> tagFilterArticles(@PathParam("id") Integer id) {
        return this.articleService.filterByTag(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public List<Article> searchByText(SearchRequest request) {
        return this.articleService.searchByText(request.getText());
    }

    @PUT
    @Path("/article/increase/{id}")
    public void increaseVisits(@PathParam("id") int id) {
        this.articleService.increaseVisits(id);
    }

    @POST
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Response editArticle(@Valid EditArticleRequest editArticleRequest) {
        if (this.articleService.editArticle(editArticleRequest))
            return Response.ok().build();
        else
            return Response.status(409).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    @Path("/total")
    public int getTotal() {
        return this.articleService.getTotal();
    }

    @DELETE
    @Path("/delete/{id}")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    public void removeArticle(@PathParam("id") int id) {this.articleService.removeArticle(id);}


}

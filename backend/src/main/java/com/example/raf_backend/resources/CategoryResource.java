package com.example.raf_backend.resources;

import com.example.raf_backend.entities.Category;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.requests.EditCategoryRequest;
import com.example.raf_backend.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})

    public Response addCategory(@Valid Category category) {

        if (categoryService.addCategory(category))
            return Response.ok().build();
        else
            return Response.status(409).build();


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch/{page}")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})

    public List<Category> fetchCategories(@PathParam("page") int page) {
        return this.categoryService.fetchCategories(page);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetchNames")
    public List<String> fetchNames() {
        return this.categoryService.fetchNames();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPage")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    public int getPages() {
        return categoryService.getPages();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    public Response editCategory(@Valid EditCategoryRequest editCategoryRequest) {

        if (this.categoryService.editCategory(editCategoryRequest))
            return Response.ok().build();
        else
            return Response.status(409).build();

    }

    @DELETE
    @Path("/delete/{name}")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    public Response removeCategory(@PathParam("name") String name) {

        if (this.categoryService.removeCategory(name))
            return Response.ok().build();
        else
            return Response.status(409).build();


    }

}

package com.example.raf_backend.resources;

import com.example.raf_backend.entities.Tag;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tag")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetchTags")
    public List<String> fetchTags() {
        return this.tagService.fetchTags();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    @Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
    public Response addTag(@Valid Tag tag) {

        if (tagService.addTag(tag))
            return Response.ok().build();
        else
            return Response.status(409).build();

    }
}

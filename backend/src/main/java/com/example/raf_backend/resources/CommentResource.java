package com.example.raf_backend.resources;

import com.example.raf_backend.entities.Comment;
import com.example.raf_backend.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @POST
    public Response addComment(@Valid Comment comment) {
        if (this.commentService.addComment(comment))
            return Response.ok().build();
        else
            return Response.status(409).build();
    }



}

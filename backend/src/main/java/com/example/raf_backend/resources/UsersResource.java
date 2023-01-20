package com.example.raf_backend.resources;

import com.example.raf_backend.entities.User;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.requests.EditStatusRequest;
import com.example.raf_backend.requests.EditUserRequest;
import com.example.raf_backend.requests.LoginRequest;
import com.example.raf_backend.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/user")
public class UsersResource {

    @Inject
    private UserService userService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(@Valid LoginRequest loginRequest) {

        Map<String, String> response = new HashMap<>();


        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        String jwt = userService.login(email, password);

        if (jwt == null) {
            response.put("message", "Incorrect username of password");
            return Response.status(422, "Invalid credentials").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getNumber")
    public int getUserNumber() {return this.userService.getUserNumber();}


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch/{page}")
    public List<User> getUsers(@PathParam("page") int page) {
        return this.userService.fetchUsers(page);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    @Secured({UserType.ADMIN})
    public Response addUser(@Valid User user) {

        try {
            this.userService.addUser(user);
        } catch (Exception e) {
            return Response.status(409).build();
        }

        return Response.ok().build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editUser")
    @Secured({UserType.ADMIN})
    public Response editUser(@Valid EditUserRequest editUserRequest) {

        try {
            this.userService.editUser(editUserRequest);
        }catch (Exception e) {
            return Response.status(409).build();
        }

        return  Response.ok().build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changeStatus")
    @Secured({UserType.ADMIN})
    public Response changeStatus(@Valid EditStatusRequest editStatusRequest) {


        if (this.userService.changeStatus(editStatusRequest))
            return Response.ok().build();
        else
            return Response.status(409).build();


    }


}

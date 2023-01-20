package com.example.raf_backend.resources;

import javax.ws.rs.Path;

@Path("/test")
public class test {

    @Path("/hello")
    public String hello() {
        return "Helo world";
    }
}

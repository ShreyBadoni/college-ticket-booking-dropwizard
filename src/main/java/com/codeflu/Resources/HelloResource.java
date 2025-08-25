package com.codeflu.Resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private String name;

    public HelloResource() {
        this.name = "World";
    }

    public HelloResource(String name) {
        this.name = name;
    }

    @GET
    public String sayHello() {
        return "Hello, " + name + "!";
    }
}

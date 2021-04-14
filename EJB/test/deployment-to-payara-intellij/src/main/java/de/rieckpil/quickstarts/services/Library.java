package de.rieckpil.quickstarts.services;

import de.rieckpil.quickstarts.bbeans.LibrarySessionBean;
import de.rieckpil.quickstarts.models.Result;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("library")
public class Library {

    @Inject
    private LibrarySessionBean librarySessionBean;

    @GET
    public Response message(@QueryParam("name") String name, @Context UriInfo uriInfo) {
        Result result = new Result();
        librarySessionBean.addBook(name);
        result.setData(librarySessionBean.getBooks());
        result.setStatus(200);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @POST
    @Produces({"application/json; qs=1"})
    public Response removeBook(@QueryParam("name") String name, @Context UriInfo uriInfo) {
        Result result = new Result();
        result.setStatus(200);
        result.setData(librarySessionBean.addBook(name));
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json; qs=1"})
    public Response removeBook(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Result result = new Result();
        result.setStatus(200);
        librarySessionBean.removeBook(id);
        result.setData(id);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }
}

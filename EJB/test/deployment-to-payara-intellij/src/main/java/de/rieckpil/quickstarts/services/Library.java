package de.rieckpil.quickstarts.services;

import de.rieckpil.quickstarts.bbeans.LibrarySessionBean;
import de.rieckpil.quickstarts.models.Result;
import jakarta.ejb.EJB;
import jakarta.ejb.Remove;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("library")
public class Library {

    //    @EJB
    @Inject
//    @ConfigProperty(name = "de.rieckpil.quickstarts.bbeans.LibrarySessionBean")
    private LibrarySessionBean librarySessionBean;

    @GET
    public Response getAll(@Context UriInfo uriInfo) {
        Result result = new Result();
        result.setData(librarySessionBean.getBooks());
        result.setStatus(200);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") int id, @Context UriInfo uriInfo) {
        System.out.println("id = " + id);
        Result result = new Result();
        result.setData(librarySessionBean.findBook(id));
        result.setStatus(200);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @POST
    @Produces({"application/json; qs=1"})
    public Response addBook(String name, @Context UriInfo uriInfo) {
        System.out.println("book name = " + name);
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
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @DELETE
    @Produces({"application/json; qs=1"})
    public Response removeAll(@Context UriInfo uriInfo) {
        System.out.println("Library.removeAll");
        Result result = new Result();
        result.setStatus(200);
        librarySessionBean.remove();
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @POST
    @Path("owner")
    @Produces({"application/json; qs=1"})
    public Response setOwnerName(String name, @Context UriInfo uriInfo) {
        System.out.println("owner name = " + name);
        Result result = new Result();
        result.setStatus(200);
        librarySessionBean.setOwnerName(name);
        result.setData(name);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @GET
    @Path("owner")
    @Produces({"application/json; qs=1"})
    public Response getOwnerName(@Context UriInfo uriInfo) {
        System.out.println("Library.getOwnerName");
        Result result = new Result();
        result.setStatus(200);
        result.setData(librarySessionBean.getOwnerName());
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }
}

package de.rieckpil.quickstarts.services;

import de.rieckpil.quickstarts.bbeans.HelloBean;
import de.rieckpil.quickstarts.bbeans.LibrarySessionBean;
import de.rieckpil.quickstarts.models.CustomLink;
import de.rieckpil.quickstarts.models.Result;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("helloEjb")
public class HelloEjb {

    //    @EJB
    @Inject
    private HelloBean helloBean;

    @GET
    public Response message(@Context UriInfo uriInfo) {
        Result result = new Result();
        result.setData(helloBean.sayHello());
        result.setStatus(200);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());

        return Response.ok(result).build();
    }

}

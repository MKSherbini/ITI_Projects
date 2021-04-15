package de.rieckpil.quickstarts.services;

import de.rieckpil.quickstarts.bbeans.ConverterBean;
import de.rieckpil.quickstarts.bbeans.CounterBean;
import de.rieckpil.quickstarts.models.Result;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.math.BigDecimal;

@Path("hits")
public class Hits {

    @Inject
    private CounterBean counterBean;

    @GET
    public Response getBook(@Context UriInfo uriInfo) {
        Result result = new Result();
        result.setData(counterBean.getHits());
        result.setStatus(200);
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

    @POST
    @Produces({"application/json; qs=1"})
    public Response addBook(@Context UriInfo uriInfo) {
        Result result = new Result();
        result.setStatus(200);
        counterBean.incrementHits();
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }


}

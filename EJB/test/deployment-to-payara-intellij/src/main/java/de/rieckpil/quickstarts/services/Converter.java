package de.rieckpil.quickstarts.services;

import de.rieckpil.quickstarts.bbeans.ConverterBean;
import de.rieckpil.quickstarts.bbeans.HelloBean;
import de.rieckpil.quickstarts.models.CustomLink;
import de.rieckpil.quickstarts.models.Result;
import jakarta.ws.rs.*;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.math.BigDecimal;

@Path("converter")
public class Converter {

    @Inject
    private ConverterBean converterBean;

    @GET
    @Path("{num}")
    @Produces({"application/json; qs=1"})
    public Response convert(@PathParam("num") int num, @QueryParam("method") String method, @Context UriInfo uriInfo) {
        Result result = new Result();
        result.setStatus(200);
        if (method != null && method.equals("dollarToYen")) {
            result.setData(converterBean.dollarToYen(new BigDecimal(num)));
        } else if (method != null && method.equals("yenToEuro")) {
            result.setData(converterBean.yenToEuro(new BigDecimal(num)));
        } else {
            result.setData(method + " method is not supported");
            result.setStatus(400);
        }
        result.getLinks().put("self", uriInfo.getAbsolutePath().toString());
        return Response.ok(result).build();
    }

}

package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.PriceRemote;
import vn.edu.iuh.backend.entities.ProductPrice;
@Path("/prices")
public class PriceResource {
    @EJB
    private PriceRemote priceRemote;

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPrice(ProductPrice price) {
        priceRemote.addPrice(price);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePrice(ProductPrice price) {
        priceRemote.updatePrice(price);
        return Response.status(Response.Status.OK).build();
    }
}

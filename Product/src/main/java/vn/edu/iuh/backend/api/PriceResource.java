package vn.edu.iuh.backend.api;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.PriceRemote;
import vn.edu.iuh.backend.business.ProductBeanRemote;
import vn.edu.iuh.backend.repositories.entities.Product;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

@Path("/prices")
public class PriceResource {
    @EJB
    PriceRemote priceRemote;
    @EJB
    ProductBeanRemote productRemote;
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getAllPriceByProductId(@PathParam("id") int productId){
        try {
            return Response.ok(priceRemote.getAllPriceByProductId(productRemote.getProductById(productId))).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

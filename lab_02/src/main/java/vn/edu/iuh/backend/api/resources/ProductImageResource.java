package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.ProductImageRemote;
import vn.edu.iuh.backend.entities.ProductImage;

@Path("/productimages")
public class ProductImageResource {
    @EJB
    private ProductImageRemote productImageRemote;

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addProductImage(ProductImage image) {
        productImageRemote.addProductImage(image);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateProductImage(ProductImage product) {
        productImageRemote.updateProductImage(product);
        return Response.status(Response.Status.OK).build();
    }
}

package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.ProductRemote;
import vn.edu.iuh.backend.entities.dtos.ProductDTO;

@Path("/products")
public class ProductResource {
    @EJB
    private ProductRemote productRemote;

    @GET
    @Produces("application/json")
    public Response getAllProduct(){
        return Response.ok(productRemote.getAllProduct()).build();
    }

    @GET
    @Path("/active")
    @Produces("application/json")
    public Response getProductsActive(){
        return Response.ok(productRemote.getProductsActive()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProductById(@PathParam("id") Long id){
        return Response.ok(productRemote.getProductById(id)).build();
    }

    @GET
    @Path("/dto/{id}")
    @Produces("application/json")
    public Response getProductDTOById(@PathParam("id") Long id){
        return Response.ok(productRemote.getProductDTOById(id)).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addProduct(ProductDTO product){
        productRemote.addProduct(product);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateProduct(ProductDTO product){
        productRemote.updateProduct(product);
        return Response.status(Response.Status.OK).build();
    }
}

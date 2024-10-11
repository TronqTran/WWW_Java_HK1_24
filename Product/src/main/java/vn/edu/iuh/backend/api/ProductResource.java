package vn.edu.iuh.backend.api;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import vn.edu.iuh.backend.business.ProductBeanRemote;
import vn.edu.iuh.backend.dtos.ProductDTO;


@Path("/products")
public class ProductResource {
    @EJB
    private ProductBeanRemote productBeanRemote;

    @GET
    public Response getAll(){
        try {
            return Response.ok(productBeanRemote.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/product/{id}")
    public Response getProductById(@PathParam("id") int id){
        return Response.ok(productBeanRemote.getProductById(id)).build();
    }

    @GET
    @Path("/{id}")
    public Response getProductDTOById(@PathParam("id") int id){
        return Response.ok(productBeanRemote.getProductDTOById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/update")
    public Response updateProductDTO(ProductDTO productDTO){
        try {
            productBeanRemote.updateProductDTO(productDTO);
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDTO productDTO){
        try {
            productBeanRemote.add(productDTO);
            return Response.status(Response.Status.CREATED).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

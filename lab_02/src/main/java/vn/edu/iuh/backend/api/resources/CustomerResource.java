package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.CustomerRemote;
import vn.edu.iuh.backend.entities.Customer;

@Path("/customers")
public class CustomerResource {
    @EJB
    private CustomerRemote customerRemote;

    @GET
    @Produces("application/json")
    public Response getAllCustomer(){
        return Response.ok(customerRemote.getAllCustomer()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCustomerById(@PathParam("id") Long id){
        return Response.ok(customerRemote.getCustomerById(id)).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addCustomer(Customer customer){
        customerRemote.addCustomer(customer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateCustomer(Customer customer){
        customerRemote.updateCustomer(customer);
        return Response.status(Response.Status.OK).build();
    }
}

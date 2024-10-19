package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.OrderRemote;
import vn.edu.iuh.backend.entities.Order;
@Path("/orders")
public class OrderResource {
    @EJB
    private OrderRemote orderRemote;

    @GET
    @Produces("application/json")
    public Response getAllOrders() {
        return Response.ok(orderRemote.getAllOrders()).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addOrder(Order order) {
        orderRemote.addOrder(order);
        return Response.status(Response.Status.CREATED).build();
    }
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getOrderById(@PathParam("id") Long id) {
        Order order = orderRemote.getOrderById(id);
        return Response.ok(order).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateOrder(Order order) {
        orderRemote.updateOrder(order);
        return Response.status(Response.Status.OK).build();
    }
}

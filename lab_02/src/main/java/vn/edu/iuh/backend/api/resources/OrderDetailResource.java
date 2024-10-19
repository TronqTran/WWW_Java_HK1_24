package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.OrderDetailRemote;
import vn.edu.iuh.backend.entities.Order;
import vn.edu.iuh.backend.entities.OrderDetail;
import vn.edu.iuh.backend.entities.Product;

@Path("/orderdetails")
public class OrderDetailResource {
    @EJB
    private OrderDetailRemote detailRemote;

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addOrderDetail(OrderDetail orderDetail) {
        detailRemote.addOrderDetail(orderDetail);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateOrderDetail(OrderDetail orderDetail) {
        detailRemote.updateOrderDetail(orderDetail);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces("application/json")
    public Response getAllOrderDetails() {
        return Response.ok(detailRemote.getAllOrderDetails()).build();
    }

    @GET
    @Path("/order/{orderId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getOrderDetailByOrder(@PathParam("orderId") Long orderId) {
        Order order = new Order();
        order.setOrder_id(orderId);
        return Response.ok(detailRemote.getOrderDetailByOrder(order)).build();
    }

    @GET
    @Path("/order/{orderId}/product/{productId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getOrderDetailByOrderAndProduct(@PathParam("orderId") Long orderId, @PathParam("productId") Long productId) {
        Order order = new Order();
        order.setOrder_id(orderId);
        Product product = new Product();
        product.setProduct_id(productId);
        return Response.ok(detailRemote.getOrderDetailByOrderAndProduct(order, product)).build();
    }
}

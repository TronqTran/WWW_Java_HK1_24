package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.Order;
import vn.edu.iuh.backend.entities.OrderDetail;
import vn.edu.iuh.backend.entities.Product;

import java.util.List;

public interface OrderDetailRemote {
    void addOrderDetail(OrderDetail orderDetail);
    void updateOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getAllOrderDetails();
    List<OrderDetail> getOrderDetailByOrder(Order orderId);
    OrderDetail getOrderDetailByOrderAndProduct(Order orderId, Product productId);
}

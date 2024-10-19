package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.Order;

import java.util.List;

public interface OrderRemote {
    void addOrder(Order order);
    Order getOrderById(Long id);
    void updateOrder(Order order);
    List<Order> getAllOrders();

}

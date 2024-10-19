package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.OrderRemote;
import vn.edu.iuh.backend.entities.Order;
import vn.edu.iuh.backend.repositories.OrderRepository;

import java.util.List;

@Stateless
public class OrderBean implements OrderRemote {
    @Inject
    private OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}

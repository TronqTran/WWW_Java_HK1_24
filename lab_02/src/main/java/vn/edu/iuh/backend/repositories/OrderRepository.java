package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Order;

import java.util.List;

public class OrderRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addOrder(Order order) {
        try {
            entityManager.persist(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateOrder(Order order) {
        try {
            entityManager.merge(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Order getOrderById(Long id) {
        return entityManager.find(Order.class, id);
    }
    public List<Order> getAllOrders() {
        return entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }
}

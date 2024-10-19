package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Order;
import vn.edu.iuh.backend.entities.OrderDetail;
import vn.edu.iuh.backend.entities.Product;

import java.util.List;


public class OrderDetailRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addOrderDetail(OrderDetail orderDetail) {
        try {
            entityManager.persist(orderDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateOrderDetail(OrderDetail orderDetail) {
        try {
            entityManager.merge(orderDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<OrderDetail> getAllOrderDetails() {
        return entityManager.createNamedQuery("OrderDetail.findAll", OrderDetail.class).getResultList();
    }
    public List<OrderDetail> getOrderDetailByOrder(Order order) {
        return entityManager.createNamedQuery("OrderDetail.findByOrder", OrderDetail.class)
                .setParameter("order", order)
                .getResultList();
    }

    public OrderDetail getOrderDetailByOrderAndProduct(Order order, Product product) {
        List<OrderDetail> results = entityManager.createNamedQuery("OrderDetail.findByOrderAndProduct", OrderDetail.class)
                .setParameter("order", order)
                .setParameter("product", product)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}

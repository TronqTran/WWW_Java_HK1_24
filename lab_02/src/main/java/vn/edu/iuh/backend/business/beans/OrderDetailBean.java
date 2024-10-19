package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.OrderDetailRemote;
import vn.edu.iuh.backend.entities.Order;
import vn.edu.iuh.backend.entities.OrderDetail;
import vn.edu.iuh.backend.entities.Product;
import vn.edu.iuh.backend.repositories.OrderDetailRepository;

import java.util.List;
@Stateless
public class OrderDetailBean implements OrderDetailRemote {

    @Inject
    private OrderDetailRepository detailRepository;

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        detailRepository.addOrderDetail(orderDetail);
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        detailRepository.updateOrderDetail(orderDetail);
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return detailRepository.getAllOrderDetails();
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrder(Order orderId) {
        return detailRepository.getOrderDetailByOrder(orderId);
    }

    @Override
    public OrderDetail getOrderDetailByOrderAndProduct(Order orderId, Product productId) {
        return detailRepository.getOrderDetailByOrderAndProduct(orderId, productId);
    }
}

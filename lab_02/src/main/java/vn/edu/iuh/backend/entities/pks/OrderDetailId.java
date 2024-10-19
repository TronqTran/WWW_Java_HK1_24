package vn.edu.iuh.backend.entities.pks;

import java.io.Serializable;
import java.util.Objects;


public class OrderDetailId implements Serializable {
    private long order;
    private long product;

    public OrderDetailId() {
    }

    public OrderDetailId(long order, long product) {
        this.order = order;
        this.product = product;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;
        return order == that.order && product == that.product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}

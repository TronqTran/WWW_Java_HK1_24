package vn.edu.iuh.backend.entities;

import jakarta.persistence.*;
import vn.edu.iuh.backend.entities.pks.OrderDetailId;

import java.util.Objects;

@Entity
@Table(name = "order_detail")
@NamedQueries({
        @NamedQuery(name = "OrderDetail.findByOrder", query = "select o from OrderDetail o where o.order = :order"),
        @NamedQuery(name = "OrderDetail.findAll", query = "select o from OrderDetail o"),
        @NamedQuery(name = "OrderDetail.findByOrderAndProduct", query = "select o from OrderDetail o where o.order = :order and o.product = :product")
})

@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
    @Column(name = "note", length = 255)
    private String note;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private double quantity;


    public OrderDetail() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}

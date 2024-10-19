package vn.edu.iuh.backend.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product_price")
@NamedQueries({
        @NamedQuery(name = "ProductPrice.findALlByProductOrderByPrice_idDesc", query = "select p from ProductPrice p where p.product = :product order by p.price_id DESC"),
        @NamedQuery(name = "ProductPrice.findByProductOrderByPrice_idDesc", query = "select p from ProductPrice p where p.product = :product order by p.price_id DESC")
})
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id", nullable = false)
    private long price_id;
    @Column(name = "price_date_time")
    private Timestamp price_date_time;
    @Column(name = "note", length = 255)
    private String note;
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public ProductPrice() {
    }

    public long getPrice_id() {
        return price_id;
    }

    public void setPrice_id(long price_id) {
        this.price_id = price_id;
    }

    public Timestamp getPrice_date_time() {
        return price_date_time;
    }

    public void setPrice_date_time(Timestamp price_date_time) {
        this.price_date_time = price_date_time;
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
        ProductPrice price = (ProductPrice) o;
        return price_id == price.price_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price_id);
    }
}

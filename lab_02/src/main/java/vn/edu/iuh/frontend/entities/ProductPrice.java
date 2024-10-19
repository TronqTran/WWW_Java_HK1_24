package vn.edu.iuh.frontend.entities;


import java.sql.Timestamp;
import java.util.Objects;

public class ProductPrice {
    private long price_id;

    private Timestamp price_date_time;

    private String note;

    private double price;
    private vn.edu.iuh.frontend.entities.Product product;

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

    public vn.edu.iuh.frontend.entities.Product getProduct() {
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

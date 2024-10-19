package vn.edu.iuh.backend.repositories.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "product_price")
@NamedQueries({
        @NamedQuery(name = "ProductPrice.findAll", query = "select p from ProductPrice p"),
        @NamedQuery(name = "ProductPrice.findByPriceId", query = "select p from ProductPrice p where p.priceId = :priceId"),
        @NamedQuery(name = "ProductPrice.findActivePriceByProduct", query = "select p from ProductPrice p where p.productByProductId = :productId and p.status = 1"),
        @NamedQuery(name = "ProductPrice.findByProductOrderByApplyDateDESC", query = "select p from ProductPrice p where p.productByProductId = :productId order by p.applyDate DESC")
})
public class ProductPrice {
    @Id
    @Column(name = "price_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_price_id_seq")
    @SequenceGenerator(name = "product_price_id_seq", sequenceName = "product_price_id_seq", allocationSize = 1)
    private int priceId;
    @Basic
    @Column(name = "apply_date")
    private Timestamp applyDate;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "note")
    private String note;
    @Basic
    @Column(name = "status")
    private int status;
    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product productByProductId;

    public ProductPrice(int priceId, Timestamp applyDate, double price, String note, int status) {
        this.priceId = priceId;
        this.applyDate = applyDate;
        this.price = price;
        this.note = note;
        this.status = status;
    }

    public ProductPrice() {
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       ProductPrice that = (ProductPrice) o;

        if (priceId != that.priceId) return false;
        if (Double.compare(price, that.price) != 0) return false;
        if (applyDate != null ? !applyDate.equals(that.applyDate) : that.applyDate != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = priceId;
        result = 31 * result + (applyDate != null ? applyDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }


}

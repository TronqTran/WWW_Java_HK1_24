package vn.edu.iuh.frontend.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.sql.Timestamp;


public class ProductPrice {

    private int priceId;

    private Timestamp applyDate;

    private double price;

    private String note;

    private int status;

    private vn.edu.iuh.frontend.entities.Product productByProductId;

    public ProductPrice(int priceId,  Timestamp applyDate, double price, String note, int status) {
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

    public vn.edu.iuh.frontend.entities.Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }


}

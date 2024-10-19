package vn.edu.iuh.frontend.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public class Product {

    private int productId;

    private String name;

    private String description;

    private String imgPath;

    private Collection<ProductPrice> productPricesByProductId;
    public Product() {
    }

    public Product(int productId, String name, String description, String imgPath) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        if (productId != product.productId) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (imgPath != null ? !imgPath.equals(product.imgPath) : product.imgPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        return result;
    }

    public Collection<ProductPrice> getProductPricesByProductId() {
        return productPricesByProductId;
    }

    public void setProductPricesByProductId(Collection<ProductPrice> productPricesByProductId) {
        this.productPricesByProductId = productPricesByProductId;
    }
}
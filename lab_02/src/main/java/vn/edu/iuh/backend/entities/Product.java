package vn.edu.iuh.backend.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import vn.edu.iuh.backend.entities.enums.ProductStatus;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.findProductActive", query = "select p from Product p where p.status = :status")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private long product_id;
    @Column(name = "name", length = 150, nullable = false)
    private String name;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "manufacturer_name", length = 150)
    private String manufacturer;
    @Column(name = "unit", length = 50)
    private String unit;
    @Column(name = "status", columnDefinition = "INT(11) DEFAULT 1")
    private ProductStatus status;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonbTransient
    private List<ProductPrice> productPrice;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonbTransient
    private List<OrderDetail> orderDetails;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonbTransient
    private List<ProductImage> productImage;
    public Product() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<ProductImage> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
    }

    public List<ProductPrice> getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(List<ProductPrice> productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_id == product.product_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id);
    }
}

package vn.edu.iuh.backend.repositories.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Table(name = "product")
@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.findByProductId", query = "select p from Product p where p.productId = :productId")
})
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private int productId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "img_path")
    private String imgPath;
    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient
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
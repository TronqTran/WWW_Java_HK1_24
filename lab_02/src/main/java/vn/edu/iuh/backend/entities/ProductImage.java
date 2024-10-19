package vn.edu.iuh.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
@NamedQueries({
        @NamedQuery(name = "ProductImage.findAllByProduct", query = "select p from ProductImage p where p.product = :product"),
        @NamedQuery(name = "ProductImage.findByProductOrderByImage_idDesc", query = "select p from ProductImage p where p.product = :product order by p.image_id DESC")
})
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private long image_id;
    @Column(name = "alternative", length = 255)
    private String alternative;
    @Column(name = "path", length = 255)
    private String path;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;


    public ProductImage() {
    }

    public long getImage_id() {
        return image_id;
    }

    public void setImage_id(long image_id) {
        this.image_id = image_id;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

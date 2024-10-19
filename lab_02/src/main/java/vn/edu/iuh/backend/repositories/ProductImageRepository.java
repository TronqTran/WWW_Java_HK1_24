package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Product;
import vn.edu.iuh.backend.entities.ProductImage;

import java.util.List;

public class ProductImageRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addProductImage(ProductImage productImage) {
        try {
            entityManager.persist(productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProductImage(ProductImage productImage) {
        try {
            entityManager.merge(productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<ProductImage> getAllProductImageByProduct(Product product) {
        return entityManager.createNamedQuery("ProductImage.findAllByProduct", ProductImage.class)
                .setParameter("product", product)
                .getResultList();
    }
    public ProductImage getActiveImageByProduct(Product product) {
        return entityManager.createNamedQuery("ProductImage.findByProductOrderByImage_idDesc", ProductImage.class)
                .setParameter("product", product)
                .setMaxResults(1)
                .getSingleResult();
    }
}

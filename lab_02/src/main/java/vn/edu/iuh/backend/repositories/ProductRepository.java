package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Product;
import vn.edu.iuh.backend.entities.enums.ProductStatus;

import java.util.List;

public class ProductRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addProduct(Product product) {
        try {
            entityManager.persist(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProduct(Product product) {
        try {
            entityManager.merge(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }
    public List<Product> getAllProducts() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }
    public List<Product> getProductsActive() {
        return entityManager.createNamedQuery("Product.findProductActive", Product.class)
                .setParameter("status", ProductStatus.ACTIVE)
                .getResultList();
    }
}

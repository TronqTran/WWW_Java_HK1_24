package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.repositories.entities.Product;

import java.util.List;

public class ProductRepository {
    @PersistenceContext(name = "mypu")
    private EntityManager entityManager;
    public void add(Product product) {
         entityManager.persist(product);
    }
    public Product getById(int id) {
        return entityManager.createNamedQuery("Product.findByProductId", Product.class).setParameter("productId", id).getSingleResult();
    }
    public List<Product> getAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }
    public Product update(Product product) {
        return entityManager.merge(product);
    }
}

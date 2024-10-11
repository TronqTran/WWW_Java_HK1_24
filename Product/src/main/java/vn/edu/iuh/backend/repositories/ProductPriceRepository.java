package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.repositories.entities.Product;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

import java.util.List;

public class ProductPriceRepository {
    @PersistenceContext(name = "mypu")
    private EntityManager entityManager;
    public void add(ProductPrice productPrice) {
        entityManager.persist(productPrice);
    }
    public List<ProductPrice> getAll() {
        return entityManager.createNamedQuery("ProductPrice.findAll", ProductPrice.class).getResultList();
    }
    public ProductPrice getById(int id) {
        return entityManager.createNamedQuery("ProductPrice.findByPriceId", ProductPrice.class).setParameter("priceId", id).getSingleResult();
    }
    public List<ProductPrice> getAllPriceByProductId(Product product) {
        return entityManager.createNamedQuery("ProductPrice.findByProductOrderByApplyDateDESC", ProductPrice.class).setParameter("productId", product).getResultList();
    }
    public ProductPrice getActivePriceByProduct(Product product) {
        return entityManager.createNamedQuery("ProductPrice.findActivePriceByProduct", ProductPrice.class).setParameter("productId", product).getSingleResult();
    }

    public void updatePrice(ProductPrice productPrice) {
        entityManager.merge(productPrice);
    }
}

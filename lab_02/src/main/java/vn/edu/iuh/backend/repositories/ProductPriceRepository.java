package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Product;
import vn.edu.iuh.backend.entities.ProductPrice;

import java.util.List;

public class ProductPriceRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addProductPrice(ProductPrice productPrice) {
        try {
            entityManager.persist(productPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProductPrice(ProductPrice productPrice) {
        try {
            entityManager.merge(productPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductPrice> getALlProductPriceByProduct(Product product) {
        return entityManager.createNamedQuery("ProductPrice.findALlByProductOrderByPrice_idDesc", ProductPrice.class)
                .setParameter("product", product)
                .getResultList();
    }
    public ProductPrice getActivePriceByProduct(Product product) {
        return entityManager.createNamedQuery("ProductPrice.findByProductOrderByPrice_idDesc", ProductPrice.class)
                .setParameter("product", product)
                .setMaxResults(1)
                .getSingleResult();
    }
}

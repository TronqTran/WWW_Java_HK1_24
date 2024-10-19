package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.repositories.entities.Product;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

import java.util.List;

public interface PriceRemote {
    void add(ProductPrice productPrice);
    List<ProductPrice> getAll();
    ProductPrice getById(int id);
    List<ProductPrice> getAllPriceByProductId(Product product);
    ProductPrice getActivePriceByProduct(Product productId);
}

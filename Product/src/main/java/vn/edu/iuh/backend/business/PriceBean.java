package vn.edu.iuh.backend.business;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.backend.repositories.entities.Product;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

import java.util.List;

@Stateless
public class PriceBean implements PriceRemote {
    @Inject
    private ProductPriceRepository productPriceRepository;
    @Override
    public void add(ProductPrice productPrice) {
        productPriceRepository.add(productPrice);
    }

    @Override
    public List<ProductPrice> getAll() {
        return productPriceRepository.getAll();
    }

    @Override
    public ProductPrice getById(int id) {
        return productPriceRepository.getById(id);
    }

    @Override
    public List<ProductPrice> getAllPriceByProductId(Product product) {
        return productPriceRepository.getAllPriceByProductId(product);
    }

    @Override
    public ProductPrice getActivePriceByProduct(Product product) {
        return productPriceRepository.getActivePriceByProduct(product);
    }
}

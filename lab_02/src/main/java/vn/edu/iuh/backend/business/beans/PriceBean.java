package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.PriceRemote;
import vn.edu.iuh.backend.entities.ProductPrice;
import vn.edu.iuh.backend.entities.dtos.ProductDTO;
import vn.edu.iuh.backend.repositories.ProductPriceRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Stateless
public class PriceBean implements PriceRemote {
    @Inject
    private ProductPriceRepository priceRepository;
    @Override
    public void addPrice(ProductPrice price)  {
        priceRepository.addProductPrice(price);
    }

    @Override
    public void updatePrice(ProductPrice price) {
        priceRepository.updateProductPrice(price);
    }
}

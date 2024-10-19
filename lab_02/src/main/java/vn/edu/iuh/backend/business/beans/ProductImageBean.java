package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.ProductImageRemote;
import vn.edu.iuh.backend.entities.ProductImage;
import vn.edu.iuh.backend.entities.dtos.ProductDTO;
import vn.edu.iuh.backend.repositories.ProductImageRepository;

@Stateless
public class ProductImageBean implements ProductImageRemote {
    @Inject
    private ProductImageRepository productImageRepository;

    @Override
    public void addProductImage(ProductImage image) {
        productImageRepository.addProductImage(image);
    }

    @Override
    public void updateProductImage(ProductImage image) {
        productImageRepository.updateProductImage(image);
    }
}

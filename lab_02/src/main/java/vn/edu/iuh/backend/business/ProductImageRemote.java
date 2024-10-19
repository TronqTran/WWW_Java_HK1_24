package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.ProductImage;

public interface ProductImageRemote {
    void addProductImage(ProductImage image);
    void updateProductImage(ProductImage product);
}

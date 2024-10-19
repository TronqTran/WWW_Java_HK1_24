package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.Product;
import vn.edu.iuh.backend.entities.dtos.ProductDTO;

import java.util.List;

public interface ProductRemote {
    void addProduct(ProductDTO product);
    void updateProduct(ProductDTO product);
    List<ProductDTO> getAllProduct();
    ProductDTO getProductDTOById(Long id);
    Product getProductById(Long id);
    List<ProductDTO> getProductsActive();
}

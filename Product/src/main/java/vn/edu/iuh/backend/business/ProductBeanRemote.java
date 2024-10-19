package vn.edu.iuh.backend.business;

import jakarta.ejb.Remote;
import vn.edu.iuh.backend.dtos.ProductDTO;
import vn.edu.iuh.backend.repositories.entities.Product;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

import java.util.List;
public interface ProductBeanRemote {
    void add(ProductDTO productDTO);
    List<ProductDTO> getAll();
    Product getProductById(int id);
    ProductDTO getProductDTOById(int id);
    void updateProductDTO(ProductDTO productDTO);
}

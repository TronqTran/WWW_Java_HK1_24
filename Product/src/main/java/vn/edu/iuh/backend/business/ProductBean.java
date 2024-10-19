package vn.edu.iuh.backend.business;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.dtos.ProductDTO;
import vn.edu.iuh.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.backend.repositories.ProductRepository;
import vn.edu.iuh.backend.repositories.entities.Product;
import vn.edu.iuh.backend.repositories.entities.ProductPrice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class ProductBean implements ProductBeanRemote {
    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductPriceRepository productPriceRepository;
    @Override
    public void add(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImgPath(productDTO.getImgPath());

        ProductPrice productPrice = new ProductPrice();
        productPrice.setPrice(productDTO.getPrice());
        productPrice.setStatus(1);
        productPrice.setProductByProductId(product);
        productPrice.setApplyDate(Timestamp.valueOf(LocalDateTime.now()));
        productPrice.setNote("");
        productRepository.add(product);
        productPriceRepository.add(productPrice);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.getAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setImgPath(product.getImgPath());
            productDTO.setPrice(productPriceRepository.getActivePriceByProduct(product).getPrice());
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public ProductDTO getProductDTOById(int id) {
        Product product = productRepository.getById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImgPath(product.getImgPath());
        productDTO.setPrice(productPriceRepository.getActivePriceByProduct(product).getPrice());
        return productDTO;
    }

    @Override
    public void updateProductDTO(ProductDTO productDTO) {
        Product product = productRepository.getById(productDTO.getId());
        if (product != null) {
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setImgPath(productDTO.getImgPath());
            productRepository.update(product);

            ProductPrice productPrice = productPriceRepository.getActivePriceByProduct(product);
            productPrice.setStatus(0);
            productPriceRepository.updatePrice(productPrice);

            ProductPrice newProductPrice = new ProductPrice();
            newProductPrice.setPrice(productDTO.getPrice());
            newProductPrice.setStatus(1);
            newProductPrice.setProductByProductId(product);
            newProductPrice.setApplyDate(Timestamp.valueOf(LocalDateTime.now()));
            newProductPrice.setNote("");
            productPriceRepository.add(newProductPrice);
        }
     }

}

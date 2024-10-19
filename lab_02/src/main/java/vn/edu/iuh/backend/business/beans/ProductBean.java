package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.ProductRemote;
import vn.edu.iuh.backend.entities.ProductImage;
import vn.edu.iuh.backend.entities.ProductPrice;
import vn.edu.iuh.backend.repositories.ProductImageRepository;
import vn.edu.iuh.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.backend.repositories.ProductRepository;
import vn.edu.iuh.backend.entities.Product;
import vn.edu.iuh.backend.entities.dtos.ProductDTO;
import vn.edu.iuh.backend.entities.enums.ProductStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class ProductBean implements ProductRemote {
    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductPriceRepository priceRepository;
    @Inject
    private ProductImageRepository imageRepository;

    @Override
    public void addProduct(ProductDTO product) {

        Product productEntity = new Product();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setManufacturer(product.getManufacturer());
        productEntity.setStatus(ProductStatus.ACTIVE);
        productEntity.setUnit(product.getUnit());
        productRepository.addProduct(productEntity);

        ProductImage productImage = new ProductImage();
        productImage.setPath(product.getImgPath());
        productImage.setProduct(productEntity);
        imageRepository.addProductImage(productImage);

        ProductPrice productPrice = new ProductPrice();
        productPrice.setPrice(product.getPrice());
        productPrice.setProduct(productEntity);
        productPrice.setPrice_date_time(new Timestamp(System.currentTimeMillis()));
        priceRepository.addProductPrice(productPrice);

    }

    @Override
    public void updateProduct(ProductDTO product) {
        Product productEntity = new Product();
        productEntity.setProduct_id(product.getProduct_id());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setManufacturer(product.getManufacturer());
        productEntity.setUnit(product.getUnit());
        productRepository.updateProduct(productEntity);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.getAllProducts();
        return getProductDTOS(products);
    }

    private List<ProductDTO> getProductDTOS(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProduct_id(product.getProduct_id());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setManufacturer(product.getManufacturer());
            productDTO.setUnit(product.getUnit());
            productDTO.setStatus(product.getStatus().getValue());
            productDTO.setPrice(priceRepository.getActivePriceByProduct(product).getPrice());
            productDTO.setImgPath(imageRepository.getActiveImageByProduct(product).getPath());
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }


    @Override
    public ProductDTO getProductDTOById(Long id) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<ProductDTO> getProductsActive() {
        List<Product> products = productRepository.getProductsActive();
        return getProductDTOS(products);
    }
}

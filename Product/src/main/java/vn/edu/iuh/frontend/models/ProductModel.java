package vn.edu.iuh.frontend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import vn.edu.iuh.frontend.dtos.ProductDTO;
import vn.edu.iuh.frontend.entities.Product;
import vn.edu.iuh.frontend.entities.ProductPrice;


import java.util.List;

public class ProductModel {
    private final String URL_PRODUCT = "http://localhost:8080/Product-1.0-SNAPSHOT/api/products";
    public void add(ProductDTO productDTO) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT);
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(productDTO, MediaType.APPLICATION_JSON));
        }
    }

    public void updateProductDTO(ProductDTO productDTO) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT+ "/product/update");
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(productDTO, MediaType.APPLICATION_JSON));
        }
    }
    public List<ProductDTO> getAll() {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT);
            String jsonProduct = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductDTO> products = objectMapper.readValue(jsonProduct,
                    new TypeReference<List<ProductDTO>>() {
                    });
            return products;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDTO getProductDTOById(int id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT + "/product/" + id);
            String jsonProduct = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDTO product = objectMapper.readValue(jsonProduct, ProductDTO.class);
            return product;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

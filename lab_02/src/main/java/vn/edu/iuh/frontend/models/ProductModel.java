package vn.edu.iuh.frontend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import vn.edu.iuh.frontend.entities.Product;
import vn.edu.iuh.frontend.entities.dtos.ProductDTO;

import java.util.List;

public class ProductModel {
    final String URL_PRODUCT = "http://localhost:8080/lab_02-1.0-SNAPSHOT/api/products";

    public void addProduct(ProductDTO product) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT + "/add");
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(product, MediaType.APPLICATION_JSON));
        }
    }

    public void updateProduct(ProductDTO product) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT + "/update");
            target.request(MediaType.APPLICATION_JSON).put(Entity.entity(product, MediaType.APPLICATION_JSON));
        }
    }

    public List<ProductDTO> getAllProduct() {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT);
            String jsonProduct = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductDTO> products = objectMapper.readValue(jsonProduct,
                    new TypeReference<List<ProductDTO>>() {
                    });
            return products;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductDTO> getAllProductActive() {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT + "/active");
            String jsonProduct = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductDTO> products = objectMapper.readValue(jsonProduct,
                    new TypeReference<List<ProductDTO>>() {
                    });
            return products;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public Product getProductById(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_PRODUCT + "/" + id);
            String jsonProduct = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(jsonProduct, Product.class);
            return product;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

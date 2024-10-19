package vn.edu.iuh.frontend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import vn.edu.iuh.frontend.entities.Order;

import java.util.List;

public class OrderModel {
    private final String URL_ORDER = "http://localhost:8080/lab_02-1.0-SNAPSHOT/api/orders";

    public void addOrder(Order order) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDER + "/add");
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(order, MediaType.APPLICATION_JSON));
        }
    }

    public List<Order> getAllOrder() {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDER);
            String jsonOrder = target.request("application/json").get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            List<Order> orders = objectMapper.readValue(jsonOrder,
                    new TypeReference<List<Order>>() {
            });
            return orders;

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrderById(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDER + "/" + id);
            String jsonOrder = target.request("application/json").get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Order order = objectMapper.readValue(jsonOrder, Order.class);
            return order;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}

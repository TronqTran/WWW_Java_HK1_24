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
import vn.edu.iuh.frontend.entities.OrderDetail;

import java.util.List;

public class OrderDetailModel {
    private final String URL_ORDERDETAIL = "http://localhost:8080/lab_02-1.0-SNAPSHOT/api/orderdetails";

    public void addOrderDetail(OrderDetail orderDetail) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDERDETAIL + "/add");
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(orderDetail, MediaType.APPLICATION_JSON));
        }
    }

    public void updateOrderDetail(OrderDetail orderDetail) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDERDETAIL + "/update");
            target.request(MediaType.APPLICATION_JSON).put(Entity.entity(orderDetail, MediaType.APPLICATION_JSON));
        }
    }

    public List<OrderDetail> getOrderDetailByOrder(Long orderId) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDERDETAIL + "/order/" + orderId);
            String jsonOrderDetail = target.request("application/json").get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            List<OrderDetail> orderDetails = objectMapper.readValue(jsonOrderDetail,
                    new TypeReference<List<OrderDetail>>() {
                    });
            return orderDetails;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderDetail getOrderDetailByOrderAndProduct(Long orderId, Long productId) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_ORDERDETAIL + "/order/" + orderId + "/product/" + productId);
            String jsonOrderDetail = target.request("application/json").get().readEntity(String.class);
            if (jsonOrderDetail == null || jsonOrderDetail.isEmpty()) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            OrderDetail orderDetail = objectMapper.readValue(jsonOrderDetail,
                    new TypeReference<OrderDetail>() {
                    });
            return orderDetail;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

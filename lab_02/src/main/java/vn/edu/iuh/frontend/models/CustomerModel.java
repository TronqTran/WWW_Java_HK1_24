package vn.edu.iuh.frontend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import vn.edu.iuh.frontend.entities.Customer;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerModel {
    private final String URL_CUSTOMER = "http://localhost:8080/lab_02-1.0-SNAPSHOT/api/customers";

    public void addCustomer(Customer customer) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_CUSTOMER + "/add");
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        }
    }

    public void updateCustomer(Customer customer) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(URL_CUSTOMER + "/update");
            target.request(MediaType.APPLICATION_JSON).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
        } finally {
            client.close();
        }
    }

    public List<Customer> getAllCustomer() {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_CUSTOMER);
            String jsonCustomer = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            List<Customer> customers = objectMapper.readValue(jsonCustomer,
                    new TypeReference<List<Customer>>() {
                    });
            return customers;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public Customer getCustomerById(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_CUSTOMER + "/" + id);
            String jsonCustomer = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            Customer customer = objectMapper.readValue(jsonCustomer, Customer.class);
            return customer;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

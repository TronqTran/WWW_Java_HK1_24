package vn.edu.iuh.frontend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import vn.edu.iuh.frontend.entities.ProductPrice;


import java.sql.Timestamp;
import java.util.List;

public class PriceModel {
    private final String ADD_URL = "http://localhost:8080/Product-1.0-SNAPSHOT/api/prices";

    public List<ProductPrice> getPriceByProductId(int id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL + "/" + id);
            String jsonPrice = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Timestamp.class, new CustomTimestampDeserializer());
            objectMapper.registerModule(module);
//            objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, true);
            List<ProductPrice> prices = objectMapper.readValue(jsonPrice,
                    new TypeReference<List<ProductPrice>>() {
                    });
            return prices;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

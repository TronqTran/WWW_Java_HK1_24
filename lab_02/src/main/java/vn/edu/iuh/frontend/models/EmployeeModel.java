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
import vn.edu.iuh.frontend.entities.Employee;

import java.lang.reflect.Type;
import java.util.List;

public class EmployeeModel {
    private final String URL_EMPLOYEE = "http://localhost:8080/lab_02-1.0-SNAPSHOT/api/employees";

    public void addEmployee(Employee employee) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_EMPLOYEE + "/add");
            target.request(MediaType.APPLICATION_JSON).post(Entity.entity(employee, MediaType.APPLICATION_JSON));
        }
    }
    public void updateEmployee(Employee employee) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_EMPLOYEE + "/update");
            target.request(MediaType.APPLICATION_JSON).put(Entity.entity(employee, MediaType.APPLICATION_JSON));
        }
    }

    public List<Employee> getAllEmployee() {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_EMPLOYEE);
            String jsonEmployee = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            List<Employee> employees = objectMapper.readValue(jsonEmployee,
                    new TypeReference<List<Employee>>() {
                        @Override
                        public Type getType() {
                            return super.getType();
                        }
                    });
            return employees;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployeeById(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL_EMPLOYEE + "/" + id);
            String jsonEmployee = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Employee employee = objectMapper.readValue(jsonEmployee, Employee.class);
            return employee;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

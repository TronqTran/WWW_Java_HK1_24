package vn.edu.iuh.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.backend.entities.Customer;

import java.util.List;

public class CustomerRepository {
    @PersistenceContext(name = "lab02PU")
    private EntityManager entityManager;
    public void addCustomer(Customer customer) {
        try {
            entityManager.persist(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCustomer(Customer customer) {
        try {
            entityManager.merge(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }
    public List<Customer> getAllCustomers() {
        return entityManager.createNamedQuery("Customer.findAll").getResultList();
    }

}

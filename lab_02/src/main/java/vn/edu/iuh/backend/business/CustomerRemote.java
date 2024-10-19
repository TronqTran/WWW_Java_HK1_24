package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.Customer;

import java.util.List;

public interface CustomerRemote {
    void addCustomer (Customer customer);
    List<Customer> getAllCustomer ();
    Customer getCustomerById(Long id);
    void updateCustomer (Customer customer);
}

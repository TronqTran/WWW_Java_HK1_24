package vn.edu.iuh.backend.business.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.backend.business.CustomerRemote;
import vn.edu.iuh.backend.repositories.CustomerRepository;
import vn.edu.iuh.backend.entities.Customer;

import java.util.List;
@Stateless
public class CustomerBean implements CustomerRemote {
    @Inject
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }
}

package org.springtask.bookstore.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springtask.bookstore.Entity.Bookstore;
import org.springtask.bookstore.Entity.Customer;
import org.springtask.bookstore.Repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerCustomer(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
    }

    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer findByUsername(String username) {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer updatedCustomer) {
        customerRepository.save(updatedCustomer);
    }

    public void deleteCustomerById(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

    public void changePassword(Customer customer, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
    }


    public boolean authenticateCustomer(String username, String password) {
        Customer customer = findByUsername(username);

        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return true;
        }

        return false;
    }

}

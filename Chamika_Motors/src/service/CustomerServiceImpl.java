package service;

import dto.CustomerDto;
import repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of CustomerService.
 */
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void registerCustomer(String mobile, String name) throws Exception {
        try {
            // Check if customer already exists
            Optional<CustomerDto> existing = customerRepository.findByMobile(mobile);
            if (existing.isPresent()) {
                throw new Exception("Customer already exists with mobile: " + mobile);
            }

            customerRepository.createCustomer(mobile, name);
            logger.info("Customer registered successfully: " + mobile);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error registering customer: " + mobile, e);
            throw e;
        }
    }

    @Override
    public void updateCustomer(String mobile, String name) throws Exception {
        try {
            customerRepository.updateCustomer(mobile, name);
            logger.info("Customer updated successfully: " + mobile);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating customer: " + mobile, e);
            throw new Exception("Failed to update customer", e);
        }
    }

    @Override
    public List<CustomerDto> searchCustomers(String namePattern) throws Exception {
        try {
            return customerRepository.searchByName(namePattern);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error searching customers: " + namePattern, e);
            throw new Exception("Failed to search customers", e);
        }
    }

    @Override
    public CustomerDto getCustomer(String mobile) throws Exception {
        try {
            Optional<CustomerDto> customer = customerRepository.findByMobile(mobile);
            return customer.orElseThrow(() -> new Exception("Customer not found: " + mobile));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting customer: " + mobile, e);
            throw e;
        }
    }
}

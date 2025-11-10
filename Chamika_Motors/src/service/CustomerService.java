package service;

import dto.CustomerDto;
import java.util.List;

/**
 * Service interface for Customer operations.
 */
public interface CustomerService {
    /**
     * Registers a new customer.
     * @param mobile customer mobile
     * @param name customer name
     * @throws Exception if customer already exists or operation fails
     */
    void registerCustomer(String mobile, String name) throws Exception;

    /**
     * Updates customer information.
     * @param mobile customer mobile
     * @param name customer name
     * @throws Exception if operation fails
     */
    void updateCustomer(String mobile, String name) throws Exception;

    /**
     * Searches customers by name.
     * @param namePattern name pattern to search
     * @return list of customers
     * @throws Exception if operation fails
     */
    List<CustomerDto> searchCustomers(String namePattern) throws Exception;

    /**
     * Gets customer by mobile.
     * @param mobile customer mobile
     * @return customer DTO
     * @throws Exception if customer not found or operation fails
     */
    CustomerDto getCustomer(String mobile) throws Exception;
}

package repository;

import dto.CustomerDto;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Customer operations.
 */
public interface CustomerRepository {
    /**
     * Creates a new customer.
     * @param mobile customer mobile
     * @param name customer name
     * @throws Exception if database operation fails
     */
    void createCustomer(String mobile, String name) throws Exception;

    /**
     * Updates customer information.
     * @param mobile customer mobile
     * @param name customer name
     * @throws Exception if database operation fails
     */
    void updateCustomer(String mobile, String name) throws Exception;

    /**
     * Finds customer by mobile.
     * @param mobile customer mobile
     * @return Optional containing customer if found
     * @throws Exception if database operation fails
     */
    Optional<CustomerDto> findByMobile(String mobile) throws Exception;

    /**
     * Searches customers by name.
     * @param namePattern name pattern to search
     * @return list of customers
     * @throws Exception if database operation fails
     */
    List<CustomerDto> searchByName(String namePattern) throws Exception;

    /**
     * Gets customer points by mobile number.
     * @param mobile customer mobile
     * @return points
     * @throws Exception if database operation fails
     */
    double getCustomerPoints(String mobile) throws Exception;

    /**
     * Updates customer points.
     * @param mobile customer mobile
     * @param newPoints new points value
     * @throws Exception if database operation fails
     */
    void updateCustomerPoints(String mobile, double newPoints) throws Exception;

    /**
     * Adds points to customer account.
     * @param mobile customer mobile
     * @param points points to add
     * @throws Exception if database operation fails
     */
    void addCustomerPoints(String mobile, double points) throws Exception;
}

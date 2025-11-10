package repository;

import java.util.Map;

/**
 * Repository interface for Payment Method operations.
 */
public interface PaymentMethodRepository {
    /**
     * Finds all payment methods.
     * @return Map of payment method name to ID
     * @throws Exception if database operation fails
     */
    Map<String, String> findAllPaymentMethods() throws Exception;

    /**
     * Finds payment method ID by name.
     * @param name the payment method name
     * @return payment method ID
     * @throws Exception if database operation fails
     */
    String findIdByName(String name) throws Exception;
}

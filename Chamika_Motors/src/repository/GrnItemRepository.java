package repository;

import java.math.BigDecimal;

/**
 * Repository interface for GRN Item operations.
 */
public interface GrnItemRepository {
    /**
     * Creates a GRN item entry.
     * @param stockId the stock ID
     * @param qty quantity
     * @param buyingPrice buying price
     * @param grnId GRN ID
     * @throws Exception if database operation fails
     */
    void createGrnItem(String stockId, double qty, BigDecimal buyingPrice, String grnId) throws Exception;
}

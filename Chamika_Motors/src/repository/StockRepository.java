package repository;

import model.Grn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository interface for Stock operations.
 */
public interface StockRepository {
    /**
     * Finds stock by product details.
     * @param productId the product ID
     * @param sellingPrice the selling price
     * @param mfg manufacturing date
     * @param exp expiry date
     * @return Optional containing stock ID if found
     * @throws Exception if database operation fails
     */
    Optional<String> findStockId(String productId, BigDecimal sellingPrice, LocalDate mfg, LocalDate exp) throws Exception;

    /**
     * Gets current quantity of a stock item.
     * @param stockId the stock ID
     * @return current quantity
     * @throws Exception if database operation fails
     */
    double getStockQuantity(String stockId) throws Exception;

    /**
     * Updates stock quantity.
     * @param stockId the stock ID
     * @param newQuantity the new quantity
     * @throws Exception if database operation fails
     */
    void updateStockQuantity(String stockId, double newQuantity) throws Exception;

    /**
     * Creates new stock entry.
     * @param productId the product ID
     * @param quantity initial quantity
     * @param sellingPrice selling price
     * @param mfg manufacturing date
     * @param exp expiry date
     * @return the ID of created stock
     * @throws Exception if database operation fails
     */
    String createStock(String productId, double quantity, BigDecimal sellingPrice, 
                      LocalDate mfg, LocalDate exp) throws Exception;

    /**
     * Decreases stock quantity (for sales).
     * @param stockId the stock ID
     * @param quantity quantity to decrease
     * @throws Exception if database operation fails
     */
    void decreaseStockQuantity(String stockId, double quantity) throws Exception;
}

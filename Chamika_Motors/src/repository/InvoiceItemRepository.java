package repository;

import java.math.BigDecimal;

/**
 * Repository interface for Invoice Item operations.
 */
public interface InvoiceItemRepository {
    /**
     * Creates an invoice item entry.
     * @param stockId the stock ID
     * @param qty quantity
     * @param invoiceId invoice ID
     * @throws Exception if database operation fails
     */
    void createInvoiceItem(String stockId, String qty, String invoiceId) throws Exception;
}

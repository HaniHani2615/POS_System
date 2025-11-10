package repository;

import model.Invoice;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Invoice operations.
 */
public interface InvoiceRepository {
    /**
     * Creates a new invoice.
     * @param id invoice ID
     * @param customerMobile customer mobile
     * @param discount discount amount
     * @param paidAmount paid amount
     * @param paymentMethodId payment method ID
     * @param balance balance
     * @param dateTime date time
     * @throws Exception if database operation fails
     */
    void createInvoice(String id, String customerMobile, BigDecimal discount, 
                      BigDecimal paidAmount, String paymentMethodId, BigDecimal balance,
                      LocalDateTime dateTime) throws Exception;

    /**
     * Counts invoices by month (yyyy-MM format).
     * @param yyyyMM the month in yyyy-MM format
     * @return number of invoices
     * @throws Exception if database operation fails
     */
    int countByMonth(String yyyyMM) throws Exception;

    /**
     * Sums paid amounts by month.
     * @param yyyyMM the month in yyyy-MM format
     * @return total paid amount
     * @throws Exception if database operation fails
     */
    double sumPaidByMonth(String yyyyMM) throws Exception;

    /**
     * Finds all invoices by month.
     * @param yyyyMM the month in yyyy-MM format
     * @return list of invoices
     * @throws Exception if database operation fails
     */
    List<Invoice> findByMonth(String yyyyMM) throws Exception;
}
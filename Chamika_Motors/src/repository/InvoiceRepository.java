package repository;

import model.Invoice;
import java.util.List;

/**
 * Repository interface for Invoice operations.
 */
public interface InvoiceRepository {
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
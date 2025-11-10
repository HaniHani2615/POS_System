package repository;

import model.Grn;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for GRN operations.
 */
public interface GrnRepository {
    /**
     * Creates a new GRN.
     * @param id the GRN ID
     * @param supplierId the supplier ID
     * @param employeeMobile the employee mobile
     * @param dateTime the date time
     * @param paidAmount the paid amount
     * @throws Exception if database operation fails
     */
    void createGrn(String id, String supplierId, String employeeMobile, 
                   LocalDateTime dateTime, BigDecimal paidAmount) throws Exception;

    /**
     * Sums paid amounts by month.
     * @param yyyyMM the month in yyyy-MM format
     * @return total paid amount
     * @throws Exception if database operation fails
     */
    double sumPaidByMonth(String yyyyMM) throws Exception;

    /**
     * Finds all GRNs by month.
     * @param yyyyMM the month in yyyy-MM format
     * @return list of GRNs
     * @throws Exception if database operation fails
     */
    List<Grn> findByMonth(String yyyyMM) throws Exception;
}
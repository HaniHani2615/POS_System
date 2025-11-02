package repository;

import model.Grn;
import java.util.List;

/**
 * Repository interface for GRN operations.
 */
public interface GrnRepository {
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
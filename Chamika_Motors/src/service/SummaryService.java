package service;

import dto.SummaryDto;
import java.time.LocalDate;

/**
 * Service interface for summary operations.
 */
public interface SummaryService {
    /**
     * Gets monthly summary data.
     * @param month the month to summarize
     * @return SummaryDto containing the data
     * @throws Exception if operation fails
     */
    SummaryDto getMonthlySummary(LocalDate month) throws Exception;
}
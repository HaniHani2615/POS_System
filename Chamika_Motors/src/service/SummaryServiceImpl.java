package service;

import dto.SummaryDto;
import repository.InvoiceRepository;
import repository.GrnRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of SummaryService.
 */
public class SummaryServiceImpl implements SummaryService {

    private static final Logger logger = Logger.getLogger(SummaryServiceImpl.class.getName());
    private final InvoiceRepository invoiceRepository;
    private final GrnRepository grnRepository;

    public SummaryServiceImpl(InvoiceRepository invoiceRepository, GrnRepository grnRepository) {
        this.invoiceRepository = invoiceRepository;
        this.grnRepository = grnRepository;
    }

    @Override
    public SummaryDto getMonthlySummary(LocalDate month) throws Exception {
        String yyyyMM = month.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String monthLabel = month.format(DateTimeFormatter.ofPattern("MMMM yyyy"));

        int invoiceCount = invoiceRepository.countByMonth(yyyyMM);
        double income = invoiceRepository.sumPaidByMonth(yyyyMM);
        double expence = grnRepository.sumPaidByMonth(yyyyMM);
        double profit = income - expence;

        double total = income + expence;
        double incomePercentage = total == 0 ? 0.0 : (income / total) * 100;
        double expencePercentage = total == 0 ? 0.0 : (expence / total) * 100;

        logger.info("Calculated summary for " + monthLabel + ": count=" + invoiceCount +
                    ", income=" + income + ", expence=" + expence + ", profit=" + profit);

        return new SummaryDto(
            invoiceCount,
            BigDecimal.valueOf(income).setScale(2, RoundingMode.HALF_UP),
            BigDecimal.valueOf(expence).setScale(2, RoundingMode.HALF_UP),
            BigDecimal.valueOf(profit).setScale(2, RoundingMode.HALF_UP),
            incomePercentage,
            expencePercentage,
            monthLabel
        );
    }
}
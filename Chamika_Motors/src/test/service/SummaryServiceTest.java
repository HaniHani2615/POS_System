package test.service;

import dto.SummaryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.InvoiceRepository;
import repository.GrnRepository;
import service.SummaryServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SummaryServiceImpl.
 */
public class SummaryServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private GrnRepository grnRepository;

    private SummaryServiceImpl summaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        summaryService = new SummaryServiceImpl(invoiceRepository, grnRepository);
    }

    @Test
    void testGetMonthlySummary_NormalCase() throws Exception {
        // Given
        LocalDate month = LocalDate.of(2023, 10, 1);
        String yyyyMM = "2023-10";

        when(invoiceRepository.countByMonth(yyyyMM)).thenReturn(5);
        when(invoiceRepository.sumPaidByMonth(yyyyMM)).thenReturn(1000.0);
        when(grnRepository.sumPaidByMonth(yyyyMM)).thenReturn(600.0);

        // When
        SummaryDto result = summaryService.getMonthlySummary(month);

        // Then
        assertEquals(5, result.getInvoiceCount());
        assertEquals(1000.0, result.getIncome().doubleValue());
        assertEquals(600.0, result.getExpence().doubleValue());
        assertEquals(400.0, result.getProfit().doubleValue());
        assertEquals(62.5, result.getIncomePercentage(), 0.1);
        assertEquals(37.5, result.getExpencePercentage(), 0.1);
        assertEquals("October 2023", result.getMonthLabel());

        verify(invoiceRepository).countByMonth(yyyyMM);
        verify(invoiceRepository).sumPaidByMonth(yyyyMM);
        verify(grnRepository).sumPaidByMonth(yyyyMM);
    }

    @Test
    void testGetMonthlySummary_ZeroTotals() throws Exception {
        // Given
        LocalDate month = LocalDate.of(2023, 10, 1);
        String yyyyMM = "2023-10";

        when(invoiceRepository.countByMonth(yyyyMM)).thenReturn(0);
        when(invoiceRepository.sumPaidByMonth(yyyyMM)).thenReturn(0.0);
        when(grnRepository.sumPaidByMonth(yyyyMM)).thenReturn(0.0);

        // When
        SummaryDto result = summaryService.getMonthlySummary(month);

        // Then
        assertEquals(0, result.getInvoiceCount());
        assertEquals(0.0, result.getIncome().doubleValue());
        assertEquals(0.0, result.getExpence().doubleValue());
        assertEquals(0.0, result.getProfit().doubleValue());
        assertEquals(0.0, result.getIncomePercentage());
        assertEquals(0.0, result.getExpencePercentage());
    }

    @Test
    void testGetMonthlySummary_ExceptionHandling() throws Exception {
        // Given
        LocalDate month = LocalDate.of(2023, 10, 1);
        String yyyyMM = "2023-10";

        when(invoiceRepository.countByMonth(yyyyMM)).thenThrow(new RuntimeException("DB error"));

        // When & Then
        assertThrows(Exception.class, () -> summaryService.getMonthlySummary(month));
    }
}
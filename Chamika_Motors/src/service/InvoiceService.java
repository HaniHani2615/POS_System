package service;

import dto.InvoiceDto;
import dto.InvoiceItemDto;
import java.util.List;
import java.util.Map;

/**
 * Service interface for Invoice operations.
 */
public interface InvoiceService {
    /**
     * Saves an invoice with its items.
     * @param invoiceDto the invoice data
     * @throws Exception if operation fails
     */
    void saveInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Gets all payment methods.
     * @return Map of payment method name to ID
     * @throws Exception if operation fails
     */
    Map<String, String> getPaymentMethods() throws Exception;

    /**
     * Calculates points for an invoice amount.
     * @param amount the invoice amount
     * @return points earned
     */
    double calculatePoints(double amount);
}

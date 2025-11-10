package service;

import dto.InvoiceDto;
import dto.InvoiceItemDto;
import repository.InvoiceRepository;
import repository.InvoiceItemRepository;
import repository.StockRepository;
import repository.CustomerRepository;
import repository.PaymentMethodRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of InvoiceService.
 */
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = Logger.getLogger(InvoiceServiceImpl.class.getName());
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final StockRepository stockRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, 
                             InvoiceItemRepository invoiceItemRepository,
                             StockRepository stockRepository,
                             CustomerRepository customerRepository,
                             PaymentMethodRepository paymentMethodRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemRepository = invoiceItemRepository;
        this.stockRepository = stockRepository;
        this.customerRepository = customerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public void saveInvoice(InvoiceDto invoiceDto) throws Exception {
        try {
            // Create invoice
            invoiceRepository.createInvoice(
                invoiceDto.getId(),
                invoiceDto.getCustomerMobile(),
                invoiceDto.getDiscount(),
                invoiceDto.getPaidAmount(),
                invoiceDto.getPaymentMethodId(),
                invoiceDto.getBalance(),
                invoiceDto.getDateTime()
            );

            // Create invoice items and update stock
            for (InvoiceItemDto item : invoiceDto.getItems()) {
                invoiceItemRepository.createInvoiceItem(
                    item.getStockId(),
                    item.getQty(),
                    invoiceDto.getId()
                );

                // Decrease stock quantity
                stockRepository.decreaseStockQuantity(
                    item.getStockId(),
                    Double.parseDouble(item.getQty())
                );
            }

            // Update customer points
            if (invoiceDto.isWithdrawPoints()) {
                customerRepository.updateCustomerPoints(
                    invoiceDto.getCustomerMobile(),
                    invoiceDto.getNewPoints()
                );
            } else {
                double points = calculatePoints(invoiceDto.getPaidAmount().doubleValue());
                customerRepository.addCustomerPoints(
                    invoiceDto.getCustomerMobile(),
                    points
                );
            }

            logger.info("Invoice saved successfully: " + invoiceDto.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving invoice: " + invoiceDto.getId(), e);
            throw new Exception("Failed to save invoice", e);
        }
    }

    @Override
    public Map<String, String> getPaymentMethods() throws Exception {
        return paymentMethodRepository.findAllPaymentMethods();
    }

    @Override
    public double calculatePoints(double amount) {
        // 1 point per 100 currency units (adjust as needed)
        return Math.floor(amount / 100);
    }
}

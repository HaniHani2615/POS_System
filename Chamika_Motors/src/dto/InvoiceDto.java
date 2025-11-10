package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for Invoice data.
 */
public class InvoiceDto {
    private String id;
    private String customerMobile;
    private String customerName;
    private String employeeMobile;
    private LocalDateTime dateTime;
    private BigDecimal discount;
    private BigDecimal paidAmount;
    private BigDecimal balance;
    private String paymentMethodId;
    private String paymentMethodName;
    private boolean withdrawPoints;
    private double pointsUsed;
    private double newPoints;
    private List<InvoiceItemDto> items;

    public InvoiceDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public boolean isWithdrawPoints() {
        return withdrawPoints;
    }

    public void setWithdrawPoints(boolean withdrawPoints) {
        this.withdrawPoints = withdrawPoints;
    }

    public double getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(double pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public double getNewPoints() {
        return newPoints;
    }

    public void setNewPoints(double newPoints) {
        this.newPoints = newPoints;
    }

    public List<InvoiceItemDto> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDto> items) {
        this.items = items;
    }
}

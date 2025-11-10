package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for GRN (Goods Received Note) data.
 */
public class GrnDto {
    private String id;
    private String supplierId;
    private String supplierName;
    private String employeeMobile;
    private LocalDateTime dateTime;
    private BigDecimal paidAmount;
    private List<GrnItemDto> items;

    public GrnDto() {
    }

    public GrnDto(String id, String supplierId, String supplierName, String employeeMobile, 
                  LocalDateTime dateTime, BigDecimal paidAmount, List<GrnItemDto> items) {
        this.id = id;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.employeeMobile = employeeMobile;
        this.dateTime = dateTime;
        this.paidAmount = paidAmount;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public List<GrnItemDto> getItems() {
        return items;
    }

    public void setItems(List<GrnItemDto> items) {
        this.items = items;
    }
}

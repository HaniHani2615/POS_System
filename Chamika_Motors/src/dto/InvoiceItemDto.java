package dto;

import java.math.BigDecimal;

/**
 * DTO for Invoice Item data.
 */
public class InvoiceItemDto {
    private String stockId;
    private String brand;
    private String productName;
    private String qty;
    private BigDecimal sellingPrice;
    private BigDecimal itemTotal;

    public InvoiceItemDto() {
    }

    public InvoiceItemDto(String stockId, String brand, String productName, 
                         String qty, BigDecimal sellingPrice) {
        this.stockId = stockId;
        this.brand = brand;
        this.productName = productName;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
        this.itemTotal = sellingPrice.multiply(new BigDecimal(qty));
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getItemTotal() {
        if (itemTotal == null && sellingPrice != null && qty != null) {
            itemTotal = sellingPrice.multiply(new BigDecimal(qty));
        }
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }
}

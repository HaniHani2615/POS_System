package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for GRN Item data.
 */
public class GrnItemDto {
    private String productId;
    private String brandName;
    private String productName;
    private double qty;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
    private LocalDate mfg;
    private LocalDate exp;
    private String stockId;

    public GrnItemDto() {
    }

    public GrnItemDto(String productId, String brandName, String productName, double qty,
                      BigDecimal buyingPrice, BigDecimal sellingPrice, LocalDate mfg, LocalDate exp) {
        this.productId = productId;
        this.brandName = brandName;
        this.productName = productName;
        this.qty = qty;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.mfg = mfg;
        this.exp = exp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LocalDate getMfg() {
        return mfg;
    }

    public void setMfg(LocalDate mfg) {
        this.mfg = mfg;
    }

    public LocalDate getExp() {
        return exp;
    }

    public void setExp(LocalDate exp) {
        this.exp = exp;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public BigDecimal getItemTotal() {
        return buyingPrice.multiply(BigDecimal.valueOf(qty));
    }
}

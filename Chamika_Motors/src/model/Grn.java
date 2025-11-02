package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Model class for GRN entity.
 */
public class Grn {
    private int id;
    private LocalDateTime dateTime;
    private BigDecimal paidAmount;
    // Add other fields as needed

    public Grn() {}

    public Grn(int id, LocalDateTime dateTime, BigDecimal paidAmount) {
        this.id = id;
        this.dateTime = dateTime;
        this.paidAmount = paidAmount;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public BigDecimal getPaidAmount() { return paidAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }

    @Override
    public String toString() {
        return "Grn{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
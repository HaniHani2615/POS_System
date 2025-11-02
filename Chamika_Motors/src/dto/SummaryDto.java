package dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Summary data.
 */
public class SummaryDto {
    private int invoiceCount;
    private BigDecimal income;
    private BigDecimal expence;
    private BigDecimal profit;
    private double incomePercentage;
    private double expencePercentage;
    private String monthLabel;

    public SummaryDto() {}

    public SummaryDto(int invoiceCount, BigDecimal income, BigDecimal expence, BigDecimal profit,
                      double incomePercentage, double expencePercentage, String monthLabel) {
        this.invoiceCount = invoiceCount;
        this.income = income;
        this.expence = expence;
        this.profit = profit;
        this.incomePercentage = incomePercentage;
        this.expencePercentage = expencePercentage;
        this.monthLabel = monthLabel;
    }

    // Getters and setters
    public int getInvoiceCount() { return invoiceCount; }
    public void setInvoiceCount(int invoiceCount) { this.invoiceCount = invoiceCount; }

    public BigDecimal getIncome() { return income; }
    public void setIncome(BigDecimal income) { this.income = income; }

    public BigDecimal getExpence() { return expence; }
    public void setExpence(BigDecimal expence) { this.expence = expence; }

    public BigDecimal getProfit() { return profit; }
    public void setProfit(BigDecimal profit) { this.profit = profit; }

    public double getIncomePercentage() { return incomePercentage; }
    public void setIncomePercentage(double incomePercentage) { this.incomePercentage = incomePercentage; }

    public double getExpencePercentage() { return expencePercentage; }
    public void setExpencePercentage(double expencePercentage) { this.expencePercentage = expencePercentage; }

    public String getMonthLabel() { return monthLabel; }
    public void setMonthLabel(String monthLabel) { this.monthLabel = monthLabel; }

    @Override
    public String toString() {
        return "SummaryDto{" +
                "invoiceCount=" + invoiceCount +
                ", income=" + income +
                ", expence=" + expence +
                ", profit=" + profit +
                ", incomePercentage=" + incomePercentage +
                ", expencePercentage=" + expencePercentage +
                ", monthLabel='" + monthLabel + '\'' +
                '}';
    }
}
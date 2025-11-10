package dto;

/**
 * DTO for Customer data.
 */
public class CustomerDto {
    private String mobile;
    private String name;
    private double points;
    private int totalInvoices;
    private double pendingPayments;

    public CustomerDto() {
    }

    public CustomerDto(String mobile, String name, double points) {
        this.mobile = mobile;
        this.name = name;
        this.points = points;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getTotalInvoices() {
        return totalInvoices;
    }

    public void setTotalInvoices(int totalInvoices) {
        this.totalInvoices = totalInvoices;
    }

    public double getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(double pendingPayments) {
        this.pendingPayments = pendingPayments;
    }
}

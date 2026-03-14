import java.math.BigDecimal;

public final class PricingResult {

    private final BigDecimal monthlyRate;
    private final BigDecimal subtotal;
    private final BigDecimal campusFee;
    private final BigDecimal total;

    public PricingResult(BigDecimal monthlyRate, BigDecimal subtotal, BigDecimal campusFee, BigDecimal total) {
        this.monthlyRate = monthlyRate;
        this.subtotal = subtotal;
        this.campusFee = campusFee;
        this.total = total;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getCampusFee() {
        return campusFee;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
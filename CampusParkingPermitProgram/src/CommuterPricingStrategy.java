import java.math.BigDecimal;

public class CommuterPricingStrategy implements PricingStrategy {

    private static final BigDecimal COMMUTER_RATE = new BigDecimal("35.00");
    private static final BigDecimal COMMUTER_DISCOUNT = new BigDecimal("0.85");

    @Override
    public BigDecimal computeMonthly(PermitSelection selection) {
        return COMMUTER_RATE.multiply(COMMUTER_DISCOUNT);
    }
}
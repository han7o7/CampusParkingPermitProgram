import java.math.BigDecimal;

public class ResidentPricingStrategy implements PricingStrategy {

    private static final BigDecimal RESIDENT_RATE = new BigDecimal("45.00");

    @Override
    public BigDecimal computeMonthly(PermitSelection selection) {
        return RESIDENT_RATE;
    }
}
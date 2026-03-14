import java.math.BigDecimal;

public class CarpoolDiscount implements RateModifier {

    private static final BigDecimal CARPOOL_MULTIPLIER = new BigDecimal("0.90");

    @Override
    public BigDecimal apply(BigDecimal currentMonthly) {
        return currentMonthly.multiply(CARPOOL_MULTIPLIER);
    }
}
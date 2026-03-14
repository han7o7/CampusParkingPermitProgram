import java.math.BigDecimal;

public enum VehicleType implements RateModifier {
    CAR("1.00"),
    SUV("1.15"),
    MOTORCYCLE("0.70");

    private final BigDecimal multiplier;

    VehicleType(String multiplier) {
        this.multiplier = new BigDecimal(multiplier);
    }

    @Override
    public BigDecimal apply(BigDecimal currentMonthly) {
        return currentMonthly.multiply(multiplier);
    }
}
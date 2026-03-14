import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PricingCalculator {

    private static final BigDecimal CAMPUS_FEE_RATE = new BigDecimal("0.05");

    public PricingResult calculate(PermitSelection selection) {
        selection.validate();

        PricingStrategy strategy = createStrategy(selection.getPermitType());

        BigDecimal monthlyRate = strategy.computeMonthly(selection);

        PricingPipeline pipeline = new PricingPipeline();
        pipeline.addModifier(selection.getVehicleType());

        if (selection.isCarpool()) {
            pipeline.addModifier(new CarpoolDiscount());
        }

        monthlyRate = pipeline.applyAll(monthlyRate);

        BigDecimal subtotal = monthlyRate.multiply(BigDecimal.valueOf(selection.getMonths()));
        BigDecimal campusFee = subtotal.multiply(CAMPUS_FEE_RATE);
        BigDecimal total = subtotal.add(campusFee);

        return new PricingResult(
                round(monthlyRate),
                round(subtotal),
                round(campusFee),
                round(total)
        );
    }

    private PricingStrategy createStrategy(PermitType permitType) {
        if (permitType == PermitType.RESIDENT) {
            return new ResidentPricingStrategy();
        } else if (permitType == PermitType.COMMUTER) {
            return new CommuterPricingStrategy();
        }

        throw new InvalidSelectionException("Unsupported permit type.");
    }

    private BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
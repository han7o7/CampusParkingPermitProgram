import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PricingCalculatorTest {

    @Test
    void residentCarNoCarpoolOneMonth() {
        PermitSelection selection = new PermitSelection(PermitType.RESIDENT, VehicleType.CAR, false, 1);
        PricingCalculator calculator = new PricingCalculator();

        PricingResult result = calculator.calculate(selection);

        assertEquals(new BigDecimal("45.00"), result.getMonthlyRate());
        assertEquals(new BigDecimal("45.00"), result.getSubtotal());
        assertEquals(new BigDecimal("2.25"), result.getCampusFee());
        assertEquals(new BigDecimal("47.25"), result.getTotal());
    }

    @Test
    void commuterSuvCarpoolSixMonthsMatchesExample() {
        PermitSelection selection = new PermitSelection(PermitType.COMMUTER, VehicleType.SUV, true, 6);
        PricingCalculator calculator = new PricingCalculator();

        PricingResult result = calculator.calculate(selection);

        assertEquals(new BigDecimal("30.79"), result.getMonthlyRate());
        assertEquals(new BigDecimal("184.75"), result.getSubtotal());
        assertEquals(new BigDecimal("9.24"), result.getCampusFee());
        assertEquals(new BigDecimal("193.98"), result.getTotal());
    }

    @Test
    void invalidMonthsThrowsException() {
        assertThrows(InvalidSelectionException.class, () -> {
            new PermitSelection(PermitType.RESIDENT, VehicleType.CAR, false, 13);
        });
    }

    @Test
    void pricingStrategyPolymorphismResidentAndCommuter() {
        List<PricingStrategy> strategies = new ArrayList<>();
        strategies.add(new ResidentPricingStrategy());
        strategies.add(new CommuterPricingStrategy());

        PermitSelection residentSelection = new PermitSelection(PermitType.RESIDENT, VehicleType.CAR, false, 1);
        PermitSelection commuterSelection = new PermitSelection(PermitType.COMMUTER, VehicleType.CAR, false, 1);

        assertEquals(new BigDecimal("45.00"),
                strategies.get(0).computeMonthly(residentSelection).setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("29.75"),
                strategies.get(1).computeMonthly(commuterSelection).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void rateModifierPolymorphismVehicleAndCarpool() {
        List<RateModifier> modifiers = new ArrayList<>();
        modifiers.add(VehicleType.MOTORCYCLE);
        modifiers.add(new CarpoolDiscount());

        BigDecimal start = new BigDecimal("45.00");
        BigDecimal result = start;

        for (RateModifier modifier : modifiers) {
            result = modifier.apply(result);
        }

        assertEquals(new BigDecimal("28.35"),
                result.setScale(2, RoundingMode.HALF_UP));
    }
}
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PricingPipeline {

    private final List<RateModifier> modifiers = new ArrayList<>();

    public void addModifier(RateModifier modifier) {
        modifiers.add(modifier);
    }

    public BigDecimal applyAll(BigDecimal monthlyRate) {
        BigDecimal result = monthlyRate;

        for (RateModifier modifier : modifiers) {
            result = modifier.apply(result);
        }

        return result;
    }
}
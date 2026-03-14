import java.math.BigDecimal;

public interface RateModifier {
    BigDecimal apply(BigDecimal currentMonthly);
}
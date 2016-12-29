package experiments.transducteurs.domain.invoice;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {
    public static final Amount HUNDRED = Amount.of(100);
    public static final Amount ZERO = Amount.of(BigDecimal.ZERO);

    private final BigDecimal value;

    public static Amount of(double amountAsDouble) {
        return Amount.of(BigDecimal.valueOf(amountAsDouble));
    }

    public static Amount of(BigDecimal amountAsBigDecimal) {
        return new Amount(Objects.requireNonNull(amountAsBigDecimal));
    }

    private Amount(BigDecimal value) {
        this.value = value.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public Amount add(Amount otherAmount) {
        return new Amount(value.add(otherAmount.value));
    }

    public Amount multiply(Quantity quantity) {
        return Amount.of(value.multiply(quantity.asBigDecimal()));
    }

    public Amount divideByHundred() {
        return Amount.of(value.divide(Amount.HUNDRED.value, 2, BigDecimal.ROUND_DOWN));
    }

    public Amount multiply(Rate vatRate) {
        return Amount.of(value.multiply(vatRate.asBigDecimal()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Amount(" + value + ')';
    }
}

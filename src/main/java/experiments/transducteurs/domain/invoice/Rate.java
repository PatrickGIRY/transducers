package experiments.transducteurs.domain.invoice;

import java.math.BigDecimal;
import java.util.Objects;

class Rate {
    private final double value;

    static Rate of(double value) {
        return new Rate(value);
    }

    private Rate(double value) {
        this.value = value;
    }

    BigDecimal asBigDecimal() {
        return BigDecimal.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Double.compare(rate.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Rate(" + value + ')';
    }
}

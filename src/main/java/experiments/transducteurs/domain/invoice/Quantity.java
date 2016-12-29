package experiments.transducteurs.domain.invoice;

import java.math.BigDecimal;
import java.util.Objects;

class Quantity  {
    private final long value;

    static Quantity of(long value) {
        return new Quantity(value);
    }

    private Quantity(long value) {
        this.value = value;
    }

    BigDecimal asBigDecimal() {
        return BigDecimal.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Quantity("+ value + ')';
    }
}

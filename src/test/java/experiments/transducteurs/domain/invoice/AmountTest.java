package experiments.transducteurs.domain.invoice;

import org.junit.Test;

import static experiments.transducteurs.domain.invoice.Amount.HUNDRED;
import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {

    @Test
    public void two_amounts_with_same_value_must_be_equal() throws Exception {
        assertThat(Amount.of(213.41)).isEqualTo(Amount.of(213.41));
    }

    @Test
    public void two_amounts_with_different_value_must_not_be_equal() throws Exception {
        assertThat(Amount.of(213.41)).isNotEqualTo(Amount.of(324.57));
    }

    @Test
    public void should_add_two_amounts() throws Exception {
        assertThat(Amount.of(12.0).add(HUNDRED)).isEqualTo(Amount.of(112.0));
        assertThat(Amount.of(12.0).add(Amount.of(25.3))).isEqualTo(Amount.of(37.3));
        assertThat(Amount.of(12.5).add(Amount.of(25.3))).isEqualTo(Amount.of(37.8));
    }

    @Test
    public void should_multiply_amount_by_quantity() throws Exception {
        assertThat(Amount.of(20.0).multiply(Quantity.of(5))).isEqualTo(Amount.of(100.0));
    }

    @Test
    public void should_multiply_amount_by_rate() throws Exception {
        assertThat(Amount.of(23.0).multiply(Rate.of(20.0))).isEqualTo(Amount.of(460.0));
    }

    @Test
    public void should_divide_amount_by_hundred() throws Exception {
        assertThat(Amount.of(230.0).divideByHundred()).isEqualTo(Amount.of(2.3));
    }
}
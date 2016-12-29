package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static experiments.transducteurs.ImperativePipeline.filter;
import static experiments.transducteurs.ImperativePipeline.map;
import static experiments.transducteurs.ImperativePipeline.reduce;
import static experiments.transducteurs._0InvoicesMixin.invoices;
import static org.assertj.core.api.Assertions.assertThat;

public class _4GetAmountOfImperativePipelineAllJavaTest {

    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    Amount getAmountOf(Month month) {
        List<Invoice> invoicesOfTheMonth = filter(new Predicate<Invoice>() {
            @Override
            public boolean test(Invoice invoice) {
                return invoice.isDueFor(month);
            }
        }, invoices());
        List<Amount> includeVATAmounts = map(new Function<Invoice, Amount>() {
            @Override
            public Amount apply(Invoice invoice) {
                return invoice.totalIncludingVAT();
            }
        }, invoicesOfTheMonth);
        return reduce(new BiFunction<Amount, Amount, Amount>() {
            @Override
            public Amount apply(Amount amount, Amount otherAmount) {
                return amount.add(otherAmount);
            }
        }, Amount.ZERO, includeVATAmounts);
    }

}

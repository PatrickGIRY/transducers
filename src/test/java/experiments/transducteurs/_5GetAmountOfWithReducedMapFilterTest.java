package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static experiments.transducteurs.Pipeline.reduce;
import static experiments.transducteurs.ReduceMapFilter.filter;
import static experiments.transducteurs.ReduceMapFilter.map;
import static experiments.transducteurs._0InvoicesMixin.invoices;
import static org.assertj.core.api.Assertions.assertThat;

public class _5GetAmountOfWithReducedMapFilterTest {

    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    Amount getAmountOf(Month month) {
        List<Invoice> invoicesOfTheMonth = filter(invoice -> invoice.isDueFor(month), new ArrayList<>(), invoices());
        List<Amount> includeVATAmounts = map(Invoice::totalIncludingVAT, new ArrayList<>(), invoicesOfTheMonth);
        return reduce(Amount::add, Amount.ZERO, Amount::add, includeVATAmounts);
    }

}

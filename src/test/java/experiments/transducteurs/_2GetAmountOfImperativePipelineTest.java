package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;
import java.util.List;

import static experiments.transducteurs.ImperativePipeline.filter;
import static experiments.transducteurs.ImperativePipeline.map;
import static experiments.transducteurs.ImperativePipeline.reduce;
import static experiments.transducteurs._0InvoicesMixin.invoices;
import static org.assertj.core.api.Assertions.assertThat;

public class _2GetAmountOfImperativePipelineTest {

    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    Amount getAmountOf(Month month) {
        List<Invoice> invoicesOfTheMonth = filter(invoice -> invoice.isDueFor(month), invoices());
        List<Amount> includeVATAmounts = map(Invoice::totalIncludingVAT, invoicesOfTheMonth);
        return reduce(Amount::add, Amount.ZERO, includeVATAmounts);
    }

}

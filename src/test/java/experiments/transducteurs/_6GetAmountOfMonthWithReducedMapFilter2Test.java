package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static experiments.transducteurs._0InvoicesMixin.invoices;
import static experiments.transducteurs.Pipeline.filter;
import static experiments.transducteurs.Pipeline.map;
import static experiments.transducteurs.Pipeline.reduce;
import static org.assertj.core.api.Assertions.assertThat;

public class _6GetAmountOfMonthWithReducedMapFilter2Test {
    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    Amount getAmountOf(Month month) {
        List<Invoice> invoicesOfTheMonth = reduce(filter(invoice -> invoice.isDueFor(month)), new ArrayList<>(),  //
                CollectionUtils::addAll, invoices());
        List<Amount> includeVATAmounts = reduce(map(Invoice::totalIncludingVAT), new ArrayList<>(),  //
                CollectionUtils::addAll, invoicesOfTheMonth);
        return reduce(Amount::add, Amount.ZERO, Amount::add, includeVATAmounts);
    }


}

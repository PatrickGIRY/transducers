package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;

import static experiments.transducteurs.Pipeline.reduce;
import static experiments.transducteurs.Transducer.filter;
import static experiments.transducteurs.Transducer.map;
import static experiments.transducteurs._0InvoicesMixin.invoices;
import static org.assertj.core.api.Assertions.assertThat;

public class _7GetAmountOfWithTransducerTest {

    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    private Amount getAmountOf(Month month) {
        return reduce((filter((Invoice invoice) -> invoice.isDueFor(month))  //
                .andThen(map(Invoice::totalIncludingVAT))) //
                .apply(Amount::add), Amount.ZERO, //
                Amount::add, invoices());
    }
}

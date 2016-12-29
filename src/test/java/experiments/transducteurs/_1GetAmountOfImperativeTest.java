package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;

import static experiments.transducteurs._0InvoicesMixin.invoices;
import static org.assertj.core.api.Assertions.assertThat;

public class _1GetAmountOfImperativeTest {

    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    Amount getAmountOf(Month month) {
        Amount totalAmount = Amount.ZERO;

        for (Invoice invoice: invoices()) {
            if (invoice.isDueFor(month)) {
                totalAmount = totalAmount.add(invoice.totalIncludingVAT());
            }
        }
        return totalAmount;
    }
}

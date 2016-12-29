package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Amount;
import experiments.transducteurs.domain.invoice.Invoice;
import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static experiments.transducteurs.Pipeline.reduce;
import static experiments.transducteurs.Transducer.filter;
import static experiments.transducteurs.Transducer.map;
import static experiments.transducteurs._0InvoicesMixin.invoices;
import static org.assertj.core.api.Assertions.assertThat;

public class _8GetAmountOfWithTransducerTest {

    @Test
    public void should_get_amount_of_month() {
        assertThat(getAmountOf(Month.OCTOBER)).isEqualTo(Amount.of(78.90));
    }

    private Amount getAmountOf(Month month) {
        Transducer<Invoice, Invoice> t1 = //
                filter((Invoice invoice) -> invoice.isDueFor(month));
        Transducer<Amount, Invoice> t2 = //
                map(Invoice::totalIncludingVAT);
        Transducer<Amount, Invoice> t3 =  //
                t1.andThen(t2);
        
        return reduce(t3.apply(Amount::add), Amount.ZERO, //
                Amount::add, invoices());
    }

    @Test
    public void should_create_list_of_due_invoices() {
        Transducer<Invoice, Invoice> t1 = //
                filter((Invoice invoice) -> invoice.isDueFor(Month.OCTOBER));

        BiFunction<List<Invoice>, Invoice, List<Invoice>> reducer = t1.apply(CollectionUtils::add);

        assertThat(reduce(reducer, new ArrayList<>(), CollectionUtils::addAll, invoices())) //
        .extracting(Invoice::totalIncludingVAT).containsOnly(Amount.of(43.20), Amount.of(35.70));

    }
}

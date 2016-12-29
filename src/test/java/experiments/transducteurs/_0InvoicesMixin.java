package experiments.transducteurs;

import experiments.transducteurs.domain.invoice.Invoice;

import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static experiments.transducteurs.domain.invoice.Invoice.anInvoice;

interface _0InvoicesMixin {

    static Collection<Invoice> invoices() {
        return Arrays.asList( //
                anInvoice(invoiceBuilder -> {
                    invoiceBuilder.dueTo(Month.OCTOBER);
                    invoiceBuilder.amountExcludeVAT(36.0);
                    invoiceBuilder.vatRate(20.0);
                    invoiceBuilder.quantity(1);
                    invoiceBuilder.mustBePaid();
                }), //
                anInvoice(invoiceBuilder -> {
                    invoiceBuilder.dueTo(Month.SEPTEMBER);
                    invoiceBuilder.amountExcludeVAT(28.0);
                    invoiceBuilder.vatRate(10.0);
                    invoiceBuilder.quantity(1);
                    invoiceBuilder.isPaid();
                }), //
                anInvoice(invoiceBuilder -> {
                    invoiceBuilder.dueTo(Month.OCTOBER);
                    invoiceBuilder.amountExcludeVAT(17.0);
                    invoiceBuilder.vatRate(5.0);
                    invoiceBuilder.quantity(2);
                    invoiceBuilder.mustBePaid();
                }), //
                anInvoice(invoiceBuilder -> {
                    invoiceBuilder.dueTo(Month.OCTOBER);
                    invoiceBuilder.amountExcludeVAT(27.0);
                    invoiceBuilder.vatRate(5.0);
                    invoiceBuilder.quantity(2);
                    invoiceBuilder.isPaid();
                }));
    }
}

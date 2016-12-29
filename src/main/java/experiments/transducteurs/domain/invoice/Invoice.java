package experiments.transducteurs.domain.invoice;

import java.time.Month;
import java.util.function.Consumer;

public class Invoice {
    private final Month dueTo;
    private final Amount amountExcludeVAT;
    private final Quantity quantity;
    private final Rate vatRate;
    private final boolean paid;

    public static Invoice anInvoice(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return builder.build();
    }

    private Invoice(Month dueTo, Amount amountExcludeVAT, Quantity quantity, Rate vatRate, boolean paid) {
        this.dueTo = dueTo;
        this.amountExcludeVAT = amountExcludeVAT;
        this.quantity = quantity;
        this.vatRate = vatRate;
        this.paid = paid;
    }

    public boolean isDueFor(Month month) {
        return !paid && dueTo == month;
    }

    /*
        amountExcludeVAT + VATAmount * quantity
     */
    public Amount totalIncludingVAT() {
        return amountExcludeVAT.add(VATAmount()).multiply(quantity);
    }

    /*
        amountExcludeVAT / 100.0 * vatRate
     */
    private Amount VATAmount() {
        return amountExcludeVAT.divideByHundred().multiply(vatRate);
    }

    public static class Builder {
        private Month dueMonth;
        private Amount amountExcludeVAT;
        private Quantity quantity;
        private Rate vatRate;
        private boolean paid;

        private Builder() {}

        public void dueTo(Month month) { dueMonth = month; }

        public void amountExcludeVAT(double amountExcludeVAT) { this.amountExcludeVAT = Amount.of(amountExcludeVAT); }

        public void quantity(long quantity) { this.quantity = Quantity.of(quantity); }

        public void vatRate(double vatRate) {
            this.vatRate = Rate.of(vatRate);
        }

        public void mustBePaid() {
            paid = false;
        }

        public void isPaid() {
            paid = true;
        }

        private Invoice build() {
            return new Invoice(dueMonth, amountExcludeVAT, quantity, vatRate, paid);
        }
    }
}

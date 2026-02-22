import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final TaxRules taxRules;
    private final DiscountRules discountRules;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, TaxRules taxRules, DiscountRules discountRules,
            InvoiceFormatter formatter) {
        this.store = store;
        this.taxRules = taxRules;
        this.discountRules = discountRules;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        List<String> formattedLines = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            formattedLines.add(String.format("- %s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        double taxPct = taxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountRules.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        String printable = formatter.format(invId, formattedLines, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}

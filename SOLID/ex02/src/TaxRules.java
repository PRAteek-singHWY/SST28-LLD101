import java.util.List;

public class TaxRules {
    private final List<TaxRule> rules;

    public TaxRules(List<TaxRule> rules) {
        this.rules = rules;
    }

    public double taxPercent(String customerType) {
        for (TaxRule rule : rules) {
            if (rule.appliesTo(customerType))
                return rule.getTaxPercent();
        }
        return 8.0;
    }
}

interface TaxRule {
    boolean appliesTo(String customerType);

    double getTaxPercent();
}

class StudentTaxRule implements TaxRule {
    public boolean appliesTo(String customerType) {
        return "student".equalsIgnoreCase(customerType);
    }

    public double getTaxPercent() {
        return 5.0;
    }
}

class StaffTaxRule implements TaxRule {
    public boolean appliesTo(String customerType) {
        return "staff".equalsIgnoreCase(customerType);
    }

    public double getTaxPercent() {
        return 2.0;
    }
}

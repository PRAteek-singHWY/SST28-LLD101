import java.util.List;

public class DiscountRules {
    private final List<DiscountRule> rules;

    public DiscountRules(List<DiscountRule> rules) {
        this.rules = rules;
    }

    public double discountAmount(String customerType, double subtotal, int distinctLines) {
        for (DiscountRule rule : rules) {
            if (rule.appliesTo(customerType)) {
                return rule.calculateDiscount(subtotal, distinctLines);
            }
        }
        return 0.0;
    }
}

interface DiscountRule {
    boolean appliesTo(String customerType);

    double calculateDiscount(double subtotal, int distinctLines);
}

class StudentDiscountRule implements DiscountRule {
    public boolean appliesTo(String customerType) {
        return "student".equalsIgnoreCase(customerType);
    }

    public double calculateDiscount(double subtotal, int distinctLines) {
        if (subtotal >= 180.0)
            return 10.0;
        return 0.0;
    }
}

class StaffDiscountRule implements DiscountRule {
    public boolean appliesTo(String customerType) {
        return "staff".equalsIgnoreCase(customerType);
    }

    public double calculateDiscount(double subtotal, int distinctLines) {
        if (distinctLines >= 3)
            return 15.0;
        return 5.0;
    }
}

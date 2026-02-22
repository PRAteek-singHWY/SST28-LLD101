public interface PricingRule {
    Money calculate(BookingRequest req);
}

class RoomPricingRule implements PricingRule {
    private final int roomType;
    private final Money price;

    public RoomPricingRule(int roomType, double amount) {
        this.roomType = roomType;
        this.price = new Money(amount);
    }

    @Override
    public Money calculate(BookingRequest req) {
        if (req.roomType == roomType)
            return price;
        return new Money(0);
    }
}

class AddOnPricingRule implements PricingRule {
    private final AddOn addOn;
    private final Money price;

    public AddOnPricingRule(AddOn addOn, double amount) {
        this.addOn = addOn;
        this.price = new Money(amount);
    }

    @Override
    public Money calculate(BookingRequest req) {
        if (req.addOns.contains(addOn))
            return price;
        return new Money(0);
    }
}

import java.util.*;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        List<PricingRule> rules = Arrays.asList(
                new RoomPricingRule(LegacyRoomTypes.SINGLE, 14000.0),
                new RoomPricingRule(LegacyRoomTypes.DOUBLE, 15000.0),
                new RoomPricingRule(LegacyRoomTypes.TRIPLE, 12000.0),
                new RoomPricingRule(LegacyRoomTypes.DELUXE, 16000.0),
                new AddOnPricingRule(AddOn.MESS, 1000.0),
                new AddOnPricingRule(AddOn.LAUNDRY, 500.0),
                new AddOnPricingRule(AddOn.GYM, 300.0));
        HostelFeeCalculator calc = new HostelFeeCalculator(rules);

        Money monthly = calc.calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        FakeBookingRepo repo = new FakeBookingRepo();
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}

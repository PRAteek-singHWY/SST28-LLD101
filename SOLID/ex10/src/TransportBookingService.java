public class TransportBookingService {
    private final DistanceCalculable distCalc;
    private final DriverAllocatable driverAlloc;
    private final PaymentChargeable paymentGw;

    public TransportBookingService(DistanceCalculable distCalc, DriverAllocatable driverAlloc,
            PaymentChargeable paymentGw) {
        this.distCalc = distCalc;
        this.driverAlloc = driverAlloc;
        this.paymentGw = paymentGw;
    }

    public void book(TripRequest req) {
        double km = distCalc.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverAlloc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentGw.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}

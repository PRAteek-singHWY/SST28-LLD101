public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");

        DistanceCalculable distCalc = new DistanceCalculator();
        DriverAllocatable driverAlloc = new DriverAllocator();
        PaymentChargeable paymentGw = new PaymentGateway();

        TransportBookingService svc = new TransportBookingService(distCalc, driverAlloc, paymentGw);

        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));
        svc.book(req);
    }
}

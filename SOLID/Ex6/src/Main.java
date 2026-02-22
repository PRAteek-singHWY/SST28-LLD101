public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new NotificationSender(audit,
                notif -> {
                    if (notif.body != null && notif.body.length() > 40) {
                        throw new IllegalArgumentException("Body too long");
                    }
                },
                notif -> "EMAIL -> to=" + notif.email + " subject=" + notif.subject + " body=" + notif.body,
                new ConsoleDelivery("email sent"));

        NotificationSender sms = new NotificationSender(audit,
                notif -> {
                },
                notif -> "SMS -> to=" + notif.phone + " body=" + notif.body,
                new ConsoleDelivery("sms sent"));

        NotificationSender wa = new NotificationSender(audit,
                notif -> {
                    if (notif.phone == null || !notif.phone.startsWith("+")) {
                        throw new IllegalArgumentException("phone must start with + and country code");
                    }
                },
                notif -> "WA -> to=" + notif.phone + " body=" + notif.body,
                new ConsoleDelivery("wa sent"));

        email.send(n);
        sms.send(n);
        try {
            wa.send(n);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}

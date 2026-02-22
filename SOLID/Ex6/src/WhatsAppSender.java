public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit,
                n -> {
                    if (n.phone == null || !n.phone.startsWith("+")) {
                        throw new IllegalArgumentException("phone must start with + and country code");
                    }
                },
                n -> "WA -> to=" + n.phone + " body=" + n.body,
                new ConsoleDelivery("wa sent"));
    }
}

public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit,
                n -> {
                    if (n.body != null && n.body.length() > 40) {
                        throw new IllegalArgumentException("Body too long");
                    }
                },
                n -> "EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body,
                new ConsoleDelivery("email sent"));
    }
}

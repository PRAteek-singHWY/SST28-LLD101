public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit,
                n -> {
                },
                n -> "SMS -> to=" + n.phone + " body=" + n.body,
                new ConsoleDelivery("sms sent"));
    }
}

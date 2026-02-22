public class NotificationSender {
    protected final AuditLog audit;
    private final NotificationValidator validator;
    private final NotificationFormatter formatter;
    private final ChannelDelivery delivery;

    public NotificationSender(AuditLog audit, NotificationValidator validator, NotificationFormatter formatter,
            ChannelDelivery delivery) {
        this.audit = audit;
        this.validator = validator;
        this.formatter = formatter;
        this.delivery = delivery;
    }

    public void send(Notification n) {
        validator.validate(n);
        String formattedMsg = formatter.format(n);
        delivery.deliver(formattedMsg);
        audit.add(delivery.getAuditTag());
    }
}

interface NotificationValidator {
    void validate(Notification n);
}

interface NotificationFormatter {
    String format(Notification n);
}

interface ChannelDelivery {
    void deliver(String message);

    String getAuditTag();
}

class ConsoleDelivery implements ChannelDelivery {
    private final String auditTag;

    public ConsoleDelivery(String auditTag) {
        this.auditTag = auditTag;
    }

    @Override
    public void deliver(String message) {
        System.out.println(message);
    }

    @Override
    public String getAuditTag() {
        return auditTag;
    }
}

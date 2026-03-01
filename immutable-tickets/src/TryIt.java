import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo showing immutability guarantees:
 * - Tickets are created via Builder with centralized validation
 * - "Updates" produce new ticket instances (original unchanged)
 * - External tag mutation has no effect on the ticket
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create ticket via Builder (validation happens in build())
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // 2. "Update" via service — returns NEW ticket, original is unchanged
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);
        System.out.println("\nAfter assign + escalate (new ticket): " + t3);
        System.out.println("Original ticket unchanged:            " + t1);

        // 3. External tag mutation attempt — has no effect
        List<String> tags = t3.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nERROR: tag mutation should have been blocked!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation blocked (UnsupportedOperationException)");
        }
        System.out.println("Tags still safe: " + t3.getTags());
    }
}
